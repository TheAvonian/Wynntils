package com.wynntils.modules.questbook.overlays.ui;

import com.wynntils.core.framework.instances.PlayerInfo;
import com.wynntils.modules.questbook.instances.IconContainer;
import com.wynntils.modules.questbook.instances.QuestBookListPage;
import com.wynntils.webapi.profiles.player.PlayerStatsProfile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// for individual friend
public class FriendPage extends QuestBookListPage<StatPage> {

    private static final HashSet<FriendPage> allFriendPages = new HashSet<>();
    private PlayerStatsProfile friendInfo;

    /**
     * Base class for all questbook list pages
     *
     * @param title         a string displayed on the left page
     * @param showSearchBar boolean of whether there is a searchbar needed for that page
     * @param icon          the icon that corresponds to the page
     */
    public FriendPage(PlayerStatsProfile friendInfo, String title, boolean showSearchBar, IconContainer icon) {
        super(title, showSearchBar, icon);
        this.friendInfo = friendInfo;

        allFriendPages.remove(this);
        allFriendPages.add(this);
    }

    public PlayerStatsProfile getFriendInfo() {
        return friendInfo;
    }

    public void setFriendInfo(PlayerStatsProfile stats) {
        friendInfo = stats;
    }

    public static List<FriendPage> getAllFriends() {
        return new ArrayList<>(allFriendPages);
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof FriendPage) {
            return friendInfo.getUuid().equals(((FriendPage) other).friendInfo.getUuid());
        }
        return false;
    }
}
