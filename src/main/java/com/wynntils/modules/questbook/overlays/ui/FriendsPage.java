package com.wynntils.modules.questbook.overlays.ui;

import com.wynntils.McIf;
import com.wynntils.core.framework.rendering.SmartFontRenderer;
import com.wynntils.core.framework.rendering.colors.CommonColors;
import com.wynntils.modules.questbook.instances.IconContainer;
import com.wynntils.modules.questbook.instances.QuestBookListPage;
import com.wynntils.modules.questbook.instances.QuestBookPage;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

import static net.minecraft.client.renderer.GlStateManager.disableLighting;

public class FriendsPage extends QuestBookListPage<FriendPage> {
    private final static List<String> textLines = Arrays.asList("Here are all of your", "friends. Selecting", "a friend opens up", "their known stats.");
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
}
