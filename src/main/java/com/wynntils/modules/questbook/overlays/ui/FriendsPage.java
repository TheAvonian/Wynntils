package com.wynntils.modules.questbook.overlays.ui;

import com.wynntils.McIf;
import com.wynntils.core.framework.rendering.SmartFontRenderer;
import com.wynntils.core.framework.rendering.colors.CommonColors;
import com.wynntils.modules.core.managers.FriendManager;
import com.wynntils.modules.questbook.enums.Guides;
import com.wynntils.modules.questbook.instances.IconContainer;
import com.wynntils.modules.questbook.instances.QuestBookListPage;
import com.wynntils.modules.questbook.instances.QuestBookPage;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.TextFormatting;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static net.minecraft.client.renderer.GlStateManager.disableLighting;

public class FriendsPage extends QuestBookListPage<FriendPage> {
    private final static List<String> textLines = Arrays.asList("Here are all of your friends.", "Selecting a friend opens up", " their known stats.");
    public FriendsPage() {
        super("Friends", true, IconContainer.friendsIcon);
    }

    @Override
    public List<String> getHoveredDescription() {
        return Arrays.asList(TextFormatting.GOLD + "[>] " + TextFormatting.BOLD + "Friends", TextFormatting.GRAY + "See all your friends", TextFormatting.GRAY + "stats.", "", TextFormatting.GREEN + "Left click to select");
    }

    @Override
    protected void drawEntry(FriendPage entryInfo, int index, boolean hovered) {
        int x = width / 2;
        int y = height / 2;
        int currentY = index * 12;

        int animationTick = -1;
        if (hovered && !showAnimation) {
            if (lastTick == 0 && !animationCompleted) {
                lastTick = McIf.getSystemTime();
            }

            if (!animationCompleted) {
                animationTick = (int) (McIf.getSystemTime() - lastTick) / 2;
            } else {
                animationTick = 133;
            }

            int width = Math.min(animationTick, 133);
            render.drawRectF(background_1, x + 9, y - 96 + currentY, x + 13 + width, y - 87 + currentY);
            render.drawRectF(background_2, x + 9, y - 96 + currentY, x + 146, y - 87 + currentY);

            disableLighting();
        } else {
            if (selected == index) {
                animationCompleted = false;

                if (!showAnimation) lastTick = 0;
            }

            render.drawRectF(background_2, x + 13, y - 96 + currentY, x + 146, y - 87 + currentY);
        }

        String name = entryInfo.getTitle();
        render.drawString(name, x + 26, y - 95 + currentY, CommonColors.BLACK, SmartFontRenderer.TextAlignment.LEFT_RIGHT, SmartFontRenderer.TextShadow.NONE);
    }

    @Override
    protected String getEmptySearchString() {
        return "No Friends were found!\n";
    }

    @Override
    protected List<List<FriendPage>> getSearchResults(String currentText) {
        List<FriendPage> names = FriendPage.getAllFriends();

        return getListSplitIntoParts(names, 12);
    }

    @Override
    protected void handleEntryClick(FriendPage page, int mouseButton) {
        if (selected >= search.get(currentPage - 1).size() || selected < 0) return;

        page.open(false);
    }

    @Override
    public void postEntries(int mouseX, int mouseY, float partialTicks) {
        int x = width / 2;
        int y = height / 2;
        int posX = (x - mouseX);
        int posY = (y - mouseY);

        drawTextLines(textLines, x - 154, y - 30, 1);

        // buttons
        drawMenuButton(x, y, posX, posY);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        ScaledResolution res = new ScaledResolution(McIf.mc());
        int posX = ((res.getScaledWidth() / 2) - mouseX);
        int posY = ((res.getScaledHeight() / 2) - mouseY);

        checkMenuButton(posX, posY);

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected List<String> getHoveredText(FriendPage entryInfo) {
        return entryInfo.getHoveredDescription();
    }
}
