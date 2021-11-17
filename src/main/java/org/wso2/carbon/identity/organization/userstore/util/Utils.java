/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.identity.organization.userstore.util;

import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.organization.mgt.core.model.AuthorizedParentOrganization;
import org.wso2.carbon.identity.organization.userstore.constants.OrganizationUserStoreManagerConstants;

import java.util.List;

import static org.wso2.carbon.identity.organization.userstore.constants.OrganizationUserStoreManagerConstants.CHILD_ORG_RETRIEVAL;

public class Utils {

    public static String escapeSpecialCharacters(String text) {

        for (String character : OrganizationUserStoreManagerConstants.specialChars) {
            text = StringUtils.replace(text, character, "\\".concat(character));
        }
        return text;
    }

    public static String childOrgRetrievalScenario() {
        return System.getProperty(CHILD_ORG_RETRIEVAL);
    }

    public static String buildSearchBase(String path) {
        String[] ous = path.split("\\s*,\\s*");
        String searchBase = "";
        for (int i = ous.length-1; i>0; i--) {
            //searchBase = searchBase + ous[i] + ",ou=";
            searchBase = searchBase + "ou=" + ous[i] + ",";
        }
        return searchBase;
    }

    public static AuthorizedParentOrganization isAuthorizedParentOrganization(List<AuthorizedParentOrganization> authorizedParentOrganizations, String ou) {
        if (authorizedParentOrganizations != null && !authorizedParentOrganizations.isEmpty()) {
            for(AuthorizedParentOrganization org : authorizedParentOrganizations) {
                if (org.getDisplayName().equals(ou))
                    return org;
            }
        }
        return null;
    }
}
