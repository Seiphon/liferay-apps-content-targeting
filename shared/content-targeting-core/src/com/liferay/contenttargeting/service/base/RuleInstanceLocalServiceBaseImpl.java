/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.contenttargeting.service.base;

import com.liferay.contenttargeting.model.RuleInstance;
import com.liferay.contenttargeting.service.RuleInstanceLocalService;
import com.liferay.contenttargeting.service.persistence.CTUserPersistence;
import com.liferay.contenttargeting.service.persistence.CampaignFinder;
import com.liferay.contenttargeting.service.persistence.CampaignPersistence;
import com.liferay.contenttargeting.service.persistence.RuleInstancePersistence;
import com.liferay.contenttargeting.service.persistence.UserSegmentPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the rule instance local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.contenttargeting.service.impl.RuleInstanceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.contenttargeting.service.impl.RuleInstanceLocalServiceImpl
 * @see com.liferay.contenttargeting.service.RuleInstanceLocalServiceUtil
 * @generated
 */
public abstract class RuleInstanceLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements RuleInstanceLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.contenttargeting.service.RuleInstanceLocalServiceUtil} to access the rule instance local service.
	 */

	/**
	 * Adds the rule instance to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ruleInstance the rule instance
	 * @return the rule instance that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RuleInstance addRuleInstance(RuleInstance ruleInstance)
		throws SystemException {
		ruleInstance.setNew(true);

		return ruleInstancePersistence.update(ruleInstance);
	}

	/**
	 * Creates a new rule instance with the primary key. Does not add the rule instance to the database.
	 *
	 * @param ruleInstanceId the primary key for the new rule instance
	 * @return the new rule instance
	 */
	@Override
	public RuleInstance createRuleInstance(long ruleInstanceId) {
		return ruleInstancePersistence.create(ruleInstanceId);
	}

	/**
	 * Deletes the rule instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ruleInstanceId the primary key of the rule instance
	 * @return the rule instance that was removed
	 * @throws PortalException if a rule instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public RuleInstance deleteRuleInstance(long ruleInstanceId)
		throws PortalException, SystemException {
		return ruleInstancePersistence.remove(ruleInstanceId);
	}

	/**
	 * Deletes the rule instance from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ruleInstance the rule instance
	 * @return the rule instance that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public RuleInstance deleteRuleInstance(RuleInstance ruleInstance)
		throws SystemException {
		return ruleInstancePersistence.remove(ruleInstance);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(RuleInstance.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return ruleInstancePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.contenttargeting.model.impl.RuleInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return ruleInstancePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.contenttargeting.model.impl.RuleInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return ruleInstancePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return ruleInstancePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return ruleInstancePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public RuleInstance fetchRuleInstance(long ruleInstanceId)
		throws SystemException {
		return ruleInstancePersistence.fetchByPrimaryKey(ruleInstanceId);
	}

	/**
	 * Returns the rule instance with the matching UUID and company.
	 *
	 * @param uuid the rule instance's UUID
	 * @param  companyId the primary key of the company
	 * @return the matching rule instance, or <code>null</code> if a matching rule instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RuleInstance fetchRuleInstanceByUuidAndCompanyId(String uuid,
		long companyId) throws SystemException {
		return ruleInstancePersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the rule instance matching the UUID and group.
	 *
	 * @param uuid the rule instance's UUID
	 * @param groupId the primary key of the group
	 * @return the matching rule instance, or <code>null</code> if a matching rule instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RuleInstance fetchRuleInstanceByUuidAndGroupId(String uuid,
		long groupId) throws SystemException {
		return ruleInstancePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the rule instance with the primary key.
	 *
	 * @param ruleInstanceId the primary key of the rule instance
	 * @return the rule instance
	 * @throws PortalException if a rule instance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RuleInstance getRuleInstance(long ruleInstanceId)
		throws PortalException, SystemException {
		return ruleInstancePersistence.findByPrimaryKey(ruleInstanceId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return ruleInstancePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the rule instance with the matching UUID and company.
	 *
	 * @param uuid the rule instance's UUID
	 * @param  companyId the primary key of the company
	 * @return the matching rule instance
	 * @throws PortalException if a matching rule instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RuleInstance getRuleInstanceByUuidAndCompanyId(String uuid,
		long companyId) throws PortalException, SystemException {
		return ruleInstancePersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the rule instance matching the UUID and group.
	 *
	 * @param uuid the rule instance's UUID
	 * @param groupId the primary key of the group
	 * @return the matching rule instance
	 * @throws PortalException if a matching rule instance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public RuleInstance getRuleInstanceByUuidAndGroupId(String uuid,
		long groupId) throws PortalException, SystemException {
		return ruleInstancePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the rule instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.contenttargeting.model.impl.RuleInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rule instances
	 * @param end the upper bound of the range of rule instances (not inclusive)
	 * @return the range of rule instances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<RuleInstance> getRuleInstances(int start, int end)
		throws SystemException {
		return ruleInstancePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of rule instances.
	 *
	 * @return the number of rule instances
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getRuleInstancesCount() throws SystemException {
		return ruleInstancePersistence.countAll();
	}

	/**
	 * Updates the rule instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ruleInstance the rule instance
	 * @return the rule instance that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public RuleInstance updateRuleInstance(RuleInstance ruleInstance)
		throws SystemException {
		return ruleInstancePersistence.update(ruleInstance);
	}

	/**
	 * Returns the campaign local service.
	 *
	 * @return the campaign local service
	 */
	public com.liferay.contenttargeting.service.CampaignLocalService getCampaignLocalService() {
		return campaignLocalService;
	}

	/**
	 * Sets the campaign local service.
	 *
	 * @param campaignLocalService the campaign local service
	 */
	public void setCampaignLocalService(
		com.liferay.contenttargeting.service.CampaignLocalService campaignLocalService) {
		this.campaignLocalService = campaignLocalService;
	}

	/**
	 * Returns the campaign remote service.
	 *
	 * @return the campaign remote service
	 */
	public com.liferay.contenttargeting.service.CampaignService getCampaignService() {
		return campaignService;
	}

	/**
	 * Sets the campaign remote service.
	 *
	 * @param campaignService the campaign remote service
	 */
	public void setCampaignService(
		com.liferay.contenttargeting.service.CampaignService campaignService) {
		this.campaignService = campaignService;
	}

	/**
	 * Returns the campaign persistence.
	 *
	 * @return the campaign persistence
	 */
	public CampaignPersistence getCampaignPersistence() {
		return campaignPersistence;
	}

	/**
	 * Sets the campaign persistence.
	 *
	 * @param campaignPersistence the campaign persistence
	 */
	public void setCampaignPersistence(CampaignPersistence campaignPersistence) {
		this.campaignPersistence = campaignPersistence;
	}

	/**
	 * Returns the campaign finder.
	 *
	 * @return the campaign finder
	 */
	public CampaignFinder getCampaignFinder() {
		return campaignFinder;
	}

	/**
	 * Sets the campaign finder.
	 *
	 * @param campaignFinder the campaign finder
	 */
	public void setCampaignFinder(CampaignFinder campaignFinder) {
		this.campaignFinder = campaignFinder;
	}

	/**
	 * Returns the c t user local service.
	 *
	 * @return the c t user local service
	 */
	public com.liferay.contenttargeting.service.CTUserLocalService getCTUserLocalService() {
		return ctUserLocalService;
	}

	/**
	 * Sets the c t user local service.
	 *
	 * @param ctUserLocalService the c t user local service
	 */
	public void setCTUserLocalService(
		com.liferay.contenttargeting.service.CTUserLocalService ctUserLocalService) {
		this.ctUserLocalService = ctUserLocalService;
	}

	/**
	 * Returns the c t user remote service.
	 *
	 * @return the c t user remote service
	 */
	public com.liferay.contenttargeting.service.CTUserService getCTUserService() {
		return ctUserService;
	}

	/**
	 * Sets the c t user remote service.
	 *
	 * @param ctUserService the c t user remote service
	 */
	public void setCTUserService(
		com.liferay.contenttargeting.service.CTUserService ctUserService) {
		this.ctUserService = ctUserService;
	}

	/**
	 * Returns the c t user persistence.
	 *
	 * @return the c t user persistence
	 */
	public CTUserPersistence getCTUserPersistence() {
		return ctUserPersistence;
	}

	/**
	 * Sets the c t user persistence.
	 *
	 * @param ctUserPersistence the c t user persistence
	 */
	public void setCTUserPersistence(CTUserPersistence ctUserPersistence) {
		this.ctUserPersistence = ctUserPersistence;
	}

	/**
	 * Returns the rule instance local service.
	 *
	 * @return the rule instance local service
	 */
	public com.liferay.contenttargeting.service.RuleInstanceLocalService getRuleInstanceLocalService() {
		return ruleInstanceLocalService;
	}

	/**
	 * Sets the rule instance local service.
	 *
	 * @param ruleInstanceLocalService the rule instance local service
	 */
	public void setRuleInstanceLocalService(
		com.liferay.contenttargeting.service.RuleInstanceLocalService ruleInstanceLocalService) {
		this.ruleInstanceLocalService = ruleInstanceLocalService;
	}

	/**
	 * Returns the rule instance remote service.
	 *
	 * @return the rule instance remote service
	 */
	public com.liferay.contenttargeting.service.RuleInstanceService getRuleInstanceService() {
		return ruleInstanceService;
	}

	/**
	 * Sets the rule instance remote service.
	 *
	 * @param ruleInstanceService the rule instance remote service
	 */
	public void setRuleInstanceService(
		com.liferay.contenttargeting.service.RuleInstanceService ruleInstanceService) {
		this.ruleInstanceService = ruleInstanceService;
	}

	/**
	 * Returns the rule instance persistence.
	 *
	 * @return the rule instance persistence
	 */
	public RuleInstancePersistence getRuleInstancePersistence() {
		return ruleInstancePersistence;
	}

	/**
	 * Sets the rule instance persistence.
	 *
	 * @param ruleInstancePersistence the rule instance persistence
	 */
	public void setRuleInstancePersistence(
		RuleInstancePersistence ruleInstancePersistence) {
		this.ruleInstancePersistence = ruleInstancePersistence;
	}

	/**
	 * Returns the user segment local service.
	 *
	 * @return the user segment local service
	 */
	public com.liferay.contenttargeting.service.UserSegmentLocalService getUserSegmentLocalService() {
		return userSegmentLocalService;
	}

	/**
	 * Sets the user segment local service.
	 *
	 * @param userSegmentLocalService the user segment local service
	 */
	public void setUserSegmentLocalService(
		com.liferay.contenttargeting.service.UserSegmentLocalService userSegmentLocalService) {
		this.userSegmentLocalService = userSegmentLocalService;
	}

	/**
	 * Returns the user segment remote service.
	 *
	 * @return the user segment remote service
	 */
	public com.liferay.contenttargeting.service.UserSegmentService getUserSegmentService() {
		return userSegmentService;
	}

	/**
	 * Sets the user segment remote service.
	 *
	 * @param userSegmentService the user segment remote service
	 */
	public void setUserSegmentService(
		com.liferay.contenttargeting.service.UserSegmentService userSegmentService) {
		this.userSegmentService = userSegmentService;
	}

	/**
	 * Returns the user segment persistence.
	 *
	 * @return the user segment persistence
	 */
	public UserSegmentPersistence getUserSegmentPersistence() {
		return userSegmentPersistence;
	}

	/**
	 * Sets the user segment persistence.
	 *
	 * @param userSegmentPersistence the user segment persistence
	 */
	public void setUserSegmentPersistence(
		UserSegmentPersistence userSegmentPersistence) {
		this.userSegmentPersistence = userSegmentPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.contenttargeting.model.RuleInstance",
			ruleInstanceLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.contenttargeting.model.RuleInstance");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return RuleInstance.class;
	}

	protected String getModelClassName() {
		return RuleInstance.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = ruleInstancePersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.contenttargeting.service.CampaignLocalService.class)
	protected com.liferay.contenttargeting.service.CampaignLocalService campaignLocalService;
	@BeanReference(type = com.liferay.contenttargeting.service.CampaignService.class)
	protected com.liferay.contenttargeting.service.CampaignService campaignService;
	@BeanReference(type = CampaignPersistence.class)
	protected CampaignPersistence campaignPersistence;
	@BeanReference(type = CampaignFinder.class)
	protected CampaignFinder campaignFinder;
	@BeanReference(type = com.liferay.contenttargeting.service.CTUserLocalService.class)
	protected com.liferay.contenttargeting.service.CTUserLocalService ctUserLocalService;
	@BeanReference(type = com.liferay.contenttargeting.service.CTUserService.class)
	protected com.liferay.contenttargeting.service.CTUserService ctUserService;
	@BeanReference(type = CTUserPersistence.class)
	protected CTUserPersistence ctUserPersistence;
	@BeanReference(type = com.liferay.contenttargeting.service.RuleInstanceLocalService.class)
	protected com.liferay.contenttargeting.service.RuleInstanceLocalService ruleInstanceLocalService;
	@BeanReference(type = com.liferay.contenttargeting.service.RuleInstanceService.class)
	protected com.liferay.contenttargeting.service.RuleInstanceService ruleInstanceService;
	@BeanReference(type = RuleInstancePersistence.class)
	protected RuleInstancePersistence ruleInstancePersistence;
	@BeanReference(type = com.liferay.contenttargeting.service.UserSegmentLocalService.class)
	protected com.liferay.contenttargeting.service.UserSegmentLocalService userSegmentLocalService;
	@BeanReference(type = com.liferay.contenttargeting.service.UserSegmentService.class)
	protected com.liferay.contenttargeting.service.UserSegmentService userSegmentService;
	@BeanReference(type = UserSegmentPersistence.class)
	protected UserSegmentPersistence userSegmentPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private RuleInstanceLocalServiceClpInvoker _clpInvoker = new RuleInstanceLocalServiceClpInvoker();
}