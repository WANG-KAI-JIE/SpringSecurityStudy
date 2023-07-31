package top.kjwang.permission.spel;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.DefaultSecurityParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author kjwang
 * @date 2023/7/31
 * @description MethodSecurityEvaluationContext
 **/
public class CustomMethodSecurityEvaluationContext extends MethodBasedEvaluationContext {

	CustomMethodSecurityEvaluationContext(Authentication user, MethodInvocation mi) {
		this((Authentication) user, mi, new DefaultSecurityParameterNameDiscoverer());
	}

	CustomMethodSecurityEvaluationContext(Authentication user, MethodInvocation mi, ParameterNameDiscoverer parameterNameDiscoverer) {
		super(Objects.requireNonNull(mi.getThis()), getSpecificMethod(mi), mi.getArguments(), parameterNameDiscoverer);
	}

	CustomMethodSecurityEvaluationContext(MethodSecurityExpressionOperations root, MethodInvocation mi, ParameterNameDiscoverer parameterNameDiscoverer) {
		super(root, getSpecificMethod(mi), mi.getArguments(), parameterNameDiscoverer);
	}

	private static Method getSpecificMethod(MethodInvocation mi) {
		return AopUtils.getMostSpecificMethod(mi.getMethod(), AopProxyUtils.ultimateTargetClass(Objects.requireNonNull(mi.getThis())));
	}

}