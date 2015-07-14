/**
 *@author Chaz Kerby
 */
package com.chazwarp.miscadditions.compat;

import cpw.mods.fml.common.Loader;

public class CheckInstalled {

	public static boolean CoFHCore() {
		if (Loader.isModLoaded("CoFHCore")) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean ThermalExpansion() {
		if (Loader.isModLoaded("ThermalExpansion")) {
			return true;
		} else {
			return false;
		}
	}
}
