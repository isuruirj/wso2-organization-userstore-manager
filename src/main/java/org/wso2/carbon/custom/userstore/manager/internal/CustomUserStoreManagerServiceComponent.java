/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.custom.userstore.manager.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.custom.userstore.manager.CustomUserStoreManager;
import org.wso2.carbon.identity.organization.mgt.core.OrganizationManager;
import org.wso2.carbon.identity.organization.mgt.core.dao.OrganizationAuthorizationDaoImpl;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * OSGI service component for the custom user store manager bundle.
 */
@Component(name = "custom.userstore.manager",
           immediate = true)
public class CustomUserStoreManagerServiceComponent {

    private static final Log log = LogFactory.getLog(CustomUserStoreManagerServiceComponent.class);

    /**
     * Register custom user store manager service in the OSGI context.
     *
     * @param componentContext
     */
    @Activate
    protected void activate(ComponentContext componentContext) {

        try {
            BundleContext bundleContext = componentContext.getBundleContext();
            bundleContext.registerService(UserStoreManager.class.getName(), new CustomUserStoreManager(), null);
            if (log.isDebugEnabled()) {
                log.debug("Custom user store manager component activated successfully.");
            }
        } catch (Throwable e) {
            log.error("Error while activating custom user store manager module.", e);
        }
    }

    @Reference(name = "realm.service",
               service = org.wso2.carbon.user.core.service.RealmService.class,
               cardinality = ReferenceCardinality.MANDATORY,
               policy = ReferencePolicy.DYNAMIC,
               unbind = "unsetRealmService")
    protected void setRealmService(RealmService realmService) {

        if (log.isDebugEnabled()) {
            log.debug("Setting the Realm Service");
        }
        CustomUserStoreDataHolder.getInstance().setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {

        if (log.isDebugEnabled()) {
            log.debug("Unset the Realm Service.");
        }
        CustomUserStoreDataHolder.getInstance().setRealmService(null);
    }

    @Reference(name = "carbon.organization.mgt.component",
               service = org.wso2.carbon.identity.organization.mgt.core.OrganizationManager.class,
               cardinality = ReferenceCardinality.MANDATORY,
               policy = ReferencePolicy.DYNAMIC,
               unbind = "unsetOrganizationMgtService")
    protected void setOrganizationMgtService(OrganizationManager organizationService) {

        if (log.isDebugEnabled()) {
            log.debug("Setting the Organization Management Service");
        }
        CustomUserStoreDataHolder.getInstance().setOrganizationService(organizationService);
        CustomUserStoreDataHolder.getInstance().setOrganizationAuthDao(new OrganizationAuthorizationDaoImpl());
    }

    protected void unsetOrganizationMgtService(OrganizationManager organizationService) {

        if (log.isDebugEnabled()) {
            log.debug("Unset the OrganizationManagement Service.");
        }
        CustomUserStoreDataHolder.getInstance().setOrganizationService(null);
    }
}
