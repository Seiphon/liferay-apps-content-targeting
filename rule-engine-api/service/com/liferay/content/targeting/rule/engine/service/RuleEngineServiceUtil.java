/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.content.targeting.rule.engine.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for RuleEngine. This utility wraps
 * {@link com.liferay.content.targeting.rule.engine.service.impl.RuleEngineServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see RuleEngineService
 * @see com.liferay.content.targeting.rule.engine.service.base.RuleEngineServiceBaseImpl
 * @see com.liferay.content.targeting.rule.engine.service.impl.RuleEngineServiceImpl
 * @generated
 */
public class RuleEngineServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.content.targeting.rule.engine.service.impl.RuleEngineServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static long[] getMatchesUserSegmentIds(
		javax.servlet.http.HttpServletRequest request, long[] groupIds,
		long anonymousUserId) throws java.lang.Exception {
		return getService()
				   .getMatchesUserSegmentIds(request, groupIds, anonymousUserId);
	}

	public static long[] getMatchesUserSegmentIds(
		javax.servlet.http.HttpServletRequest request, long groupId,
		long anonymousUserId) throws java.lang.Exception {
		return getService()
				   .getMatchesUserSegmentIds(request, groupId, anonymousUserId);
	}

	public static boolean matches(
		javax.servlet.http.HttpServletRequest request, long anonymousUserId,
		com.liferay.content.targeting.model.UserSegment userSegment)
		throws java.lang.Exception {
		return getService().matches(request, anonymousUserId, userSegment);
	}

	public static void clearService() {
		_service = null;
	}

	public static RuleEngineService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					RuleEngineService.class.getName());

			if (invokableService instanceof RuleEngineService) {
				_service = (RuleEngineService)invokableService;
			}
			else {
				_service = new RuleEngineServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(RuleEngineServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(RuleEngineService service) {
	}

	private static RuleEngineService _service;
}