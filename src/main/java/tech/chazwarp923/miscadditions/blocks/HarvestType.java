package tech.chazwarp923.miscadditions.blocks;

public enum HarvestType {
	SWORD("sword"),
	AXE("axe"),
	PICKAXE("pickaxe"),
	SHOVEL("spade");

	final String type;

	HarvestType(String type) {
		this.type = type;
	}
}
