package com.wynntils.modules.questbook.overlays.ui;

import com.wynntils.modules.questbook.instances.IconContainer;
import com.wynntils.modules.questbook.instances.QuestBookListPage;

// for individual friend
public class FriendPage extends QuestBookListPage<StatPage> {
    /**
     * Base class for all questbook list pages
     *
     * @param title         a string displayed on the left page
     * @param showSearchBar boolean of whether there is a searchbar needed for that page
     * @param icon          the icon that corresponds to the page
     */
    public FriendPage(String title, boolean showSearchBar, IconContainer icon) {
        super(title, showSearchBar, icon);
    }
}
