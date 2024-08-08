package updata;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;


public class
InfiniteUseItem extends Item {

    private final int defaultColor; // 默认颜色
    private final String tooltipKey; // 用于存储工具提示的键

    public InfiniteUseItem(Item.Properties properties, String tooltipKey, int defaultColor) {
        super(properties);
        this.tooltipKey = tooltipKey;
        this.defaultColor = defaultColor != 0 ? defaultColor : 0xFFFFFF; // 使用白色作为默认颜色
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        Component customDescription = Component.translatable(tooltipKey);
        Style style = Style.EMPTY.withColor(TextColor.fromRgb(defaultColor));
        Component styledDescription = customDescription.copy().setStyle(style);
        tooltip.add(styledDescription);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.isEdible()) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        }
        return InteractionResultHolder.pass(itemStack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        // 调用超类方法以应用默认的食物效果和饱食度恢复
        ItemStack resultStack = super.finishUsingItem(stack, world, entity);
        stack.setCount(1);
        return stack;
    }




    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return stack.getItem().isEdible() ? UseAnim.EAT : UseAnim.NONE;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return stack.getItem().isEdible() ? super.getUseDuration(stack) : 0;
    }
}