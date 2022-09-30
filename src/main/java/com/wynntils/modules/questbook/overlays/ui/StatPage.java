package com.wynntils.modules.questbook.overlays.ui;

import com.wynntils.modules.questbook.instances.IconContainer;
import com.wynntils.modules.questbook.instances.QuestBookPage;

public class StatPage extends QuestBookPage {
    /**
     * Base class for all questbook pages
     *
     * @param title         a string displayed on the left page
     * @param showSearchBar boolean of whether there is a searchbar needed for that page
     * @param icon          the icon that corresponds to the page
     */
    public StatPage(String title, boolean showSearchBar, IconContainer icon) {
        super(title, showSearchBar, icon);
    }
}
