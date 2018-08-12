package com.blank.art.delegates;

/**
 * Created by : blank
 * Created on : 2018/6/26 at 17:41
 * Description:
 */

public abstract class ArtDelegate extends PermissionCheckerDelegate {

    /**
     *
     * @param <T>
     * @return
     */
    public <T extends ArtDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
