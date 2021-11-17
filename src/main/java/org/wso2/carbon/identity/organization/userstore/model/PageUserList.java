package org.wso2.carbon.identity.organization.userstore.model;

import org.wso2.carbon.user.core.common.User;

import java.util.List;

/**
 * This class represents the metadata of paginating user list
 */
public class PageUserList {

    int pageIndex;
    int offset;
    int pageSize;
    List<User> tempUserList;
    boolean incomplete;
    int previousUserListSize;

    public PageUserList(int offset, int pageSize) {
        this.offset = offset;
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<User> getTempUserList() {
        return tempUserList;
    }

    public void setTempUserList(List<User> tempUserList) {
        this.tempUserList = tempUserList;
    }

    public boolean isIncomplete() {
        return incomplete;
    }

    public void setIncomplete(boolean incomplete) {
        this.incomplete = incomplete;
    }

    public int getPreviousUserListSize() {
        return previousUserListSize;
    }

    public void setPreviousUserListSize(int previousUserListSize) {
        this.previousUserListSize = previousUserListSize;
    }
}
