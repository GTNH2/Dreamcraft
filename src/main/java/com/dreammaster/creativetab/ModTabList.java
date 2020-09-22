package com.dreammaster.creativetab;

import com.dreammaster.gthandler.CustomItemList;
import com.dreammaster.item.ItemList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import eu.usrv.yamcore.creativetabs.CreativeTabsManager;
import eu.usrv.yamcore.creativetabs.ModCreativeTab;
import eu.usrv.yamcore.items.ModItemManager;
import gregtech.api.GregTech_API;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

import static com.dreammaster.gthandler.casings.GT_Container_CasingsNH.sBlockCasingsNH;

public final class ModTabList {
	public static String ModGenericTab = "tabDreamCraftItems_Generic";
	public static String ModShapesTab = "tabDreamCraftItems_Shapes";
	public static String ModMoldsTab = "tabDreamCraftItems_Molds";
	public static String ModThaumcraftTab = "tabDreamCraftItems_Thaum";
	public static String ModCircuitsTab = "tabDreamCraftItems_Circuit";
	public static String ModFluidsTab = "tabDreamCraftFluids";
	public static String ModBlocksTab = "tabDreamCraftBlocks";
	public static String ModSpaceTab = "tabDreamCraftSpace";
	public static String ModSolarTab = "tabDreamCraftSolar";
	public static String ModBarsAndCasingsTab = "tabDreamCraftBars_Casings";
	public static String ModAdditionsToGregTechTab = "tabDreamGregTechAdditions";

    private ModTabList() {}

    public static void InitModTabs(CreativeTabsManager pTabManager, ModItemManager pItemManager)
	{
		pTabManager.AddCreativeTab(new CustomModTab(ModGenericTab, ItemList.AsteroidsStoneDust.getIS()));
		pTabManager.AddCreativeTab(new CustomModTab(ModShapesTab, ItemList.ShapeBlock.getIS()));
		pTabManager.AddCreativeTab(new CustomModTab(ModMoldsTab, ItemList.MoldFormAnvil.getIS()));
		pTabManager.AddCreativeTab(new CustomModTab(ModThaumcraftTab, ItemList.ChargedVoidWandCap.getIS()));
		pTabManager.AddCreativeTab(new CustomModTab(ModCircuitsTab, ItemList.QuantumCircuit.getIS()));
		pTabManager.AddCreativeTab(new CustomModTab(ModFluidsTab, Items.bucket));
		pTabManager.AddCreativeTab(new CustomModTab(ModBlocksTab, Item.getItemFromBlock(Blocks.stone)));
		pTabManager.AddCreativeTab(new CustomModTab(ModSpaceTab, ItemList.HeavyDutyNoseConeTier3.getIS()));
		pTabManager.AddCreativeTab(new CustomModTab(ModSolarTab, ItemList.EnrichedNaquadriaNeutroniumSunnariumAlloy.getIS()));
		pTabManager.AddCreativeTab(new CustomModTab(ModBarsAndCasingsTab, ItemList.ChromeBars.getIS()));
		pTabManager.AddCreativeTab(new CustomModTab(ModAdditionsToGregTechTab, ItemList.EtchedLudicrousVoltageWiring.getIS()){
			@Override
			public void displayAllReleventItems(List stuffToShow) {
				//casing adder
				for(int i = 0; i < 16; ++i) {
					ItemStack aStack = new ItemStack(sBlockCasingsNH, 1, i);
					if(!aStack.getDisplayName().contains(".name")) {
						stuffToShow.add(aStack);
					}
				}
				//te adder
				for(CustomItemList item: CustomItemList.values()){
					if (item.hasBeenSet() && item.getBlock() == GregTech_API.sBlockMachines) {
						stuffToShow.add(item.get(1));
					}
				}
				super.displayAllReleventItems(stuffToShow);
			}
		});
	}
	static class CustomModTab extends ModCreativeTab {

    	ItemStack ref;

		public CustomModTab(String lable, Item stack) {
			super(lable,stack);
			this.ref = new ItemStack(stack);
		}

		public CustomModTab(String lable, ItemStack stack) {
			super(lable,stack.getItem());
			this.ref = stack;
		}

		@Override
		@SideOnly(Side.CLIENT)
		public int func_151243_f() {
			return ref.getItemDamage();
		}
	}
}
