package top.kjwang.permission.spel;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.EvaluationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description CustomMethodSecurityExpressionHandler
 **/
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

	@Override
	public EvaluationContext createEvaluationContext(Supplier<Authentication> authentication, MethodInvocation mi) {
		MethodSecurityExpressionOperations root = this.createSecurityExpressionRoot(authentication, mi);
		CustomMethodSecurityEvaluationContext ctx = new CustomMethodSecurityEvaluationContext(root, mi, this.getParameterNameDiscoverer());
		ctx.setBeanResolver(this.getBeanResolver());
		return ctx;
	}

	public MethodSecurityExpressionOperations createSecurityExpressionRoot(Supplier<Authentication> authentication, MethodInvocation invocation) {
		CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication.get());
		root.setThis(invocation.getThis());
		root.setPermissionEvaluator(this.getPermissionEvaluator());
		root.setTrustResolver(this.getTrustResolver());
		root.setRoleHierarchy(this.getRoleHierarchy());
		root.setDefaultRolePrefix(this.getDefaultRolePrefix());
		return root;
	}
}

