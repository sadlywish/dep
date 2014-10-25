package data.hullmods;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BuffManagerAPI;
import com.fs.starfarer.api.campaign.BuffManagerAPI.Buff;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.combat.HullModEffect;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.combat.ShipVariantAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.RepairTrackerAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FleetLogistics
  implements HullModEffect
{
  public static final String HULLMOD_ID = "FleetTest";
  public static final String REPAIR_GANTRY_KEY = "RepairGantry_PersistentBuffs";
  private static final Map Logistics_BONUS = new HashMap();
  static {
	  Logistics_BONUS.put(HullSize.FRIGATE, 5f);
	  Logistics_BONUS.put(HullSize.DESTROYER, 12f);
	  Logistics_BONUS.put(HullSize.CRUISER, 25f);
	  Logistics_BONUS.put(HullSize.CAPITAL_SHIP, 50f);
  }
  public static final float CARGO_PENALTY=0.1f;
  public void advanceInCampaign(FleetMemberAPI member, float amount) {
		if (member.getFleetData() == null) return;
		PersonAPI fleet = member.getFleetCommander();
		MutableCharacterStatsAPI stats = fleet.getStats();
		List shipList = member.getFleetData().getMembersListCopy();
		float logistics = sum(shipList);
		stats.getLogistics().modifyFlat("舰队物流管理模块",logistics);
		return;
  }
  private float sum(List shipList) {
	  float sum = 0f;
		for (int i = 0; i < shipList.size(); i++) {
			FleetMemberAPI ship = (FleetMemberAPI)shipList.get(i);
			List hullmod = ship.getVariant().getHullMods();
			for (int j = 0; j < hullmod.size(); j++) {
				if (hullmod.get(j).equals("FleetLogistics")) {
					sum += (Float)Logistics_BONUS.get(ship.getHullSpec().getHullSize());
					break;
				}
			}
		}
		return sum;
  }
  /*
  private void intiIDList(){
	  IDList = new Array();
  }List
  private float addID(String ID){
	  for(int i=0;i<IDList.size();i++){
		  if(ID.equals(IDList.get(i))){
			  intiIDList();
			  break;
		  }
	  }
	  IDList.add(ID);
	  return (float) IDList.size();
  }
*/

  public void advanceInCombat(ShipAPI ship, float amount) {
  }

  public void applyEffectsAfterShipCreation(ShipAPI ship, String id) {
  }

  public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
	  stats.getCargoMod().modifyMult(id, CARGO_PENALTY);
  }

  public boolean isApplicableToShip(ShipAPI ship) {
	  String hullId = ship.getHullSpec().getHullId();
	  String[] Logistics = limitship.Logistics;
	  for (int i = 0; i < Logistics.length; i++) {
		if (hullId.equals(Logistics[i])) {
			return true;
		}
	}
	  return false;
  }

  public String getDescriptionParam(int index, ShipAPI.HullSize hullSize)
  {
    if (index == 0) return ""+Logistics_BONUS.get(hullSize);
    if (index == 1) return ""+(1-CARGO_PENALTY)*100f;
    return null;
  }
}