package data.scripts.world;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.Script;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FleetAssignment;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.campaign.StarSystemAPI;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import com.fs.starfarer.settings.StarfarerSettings;

public class CheckPoint 
{
	private SectorAPI sector;

	public CheckPoint(SectorAPI sector) {
		this.sector = sector;
	}
	public void run(){
		int i = 0;
		SectorEntityToken playerfleet;
		CargoAPI playercargo;
		for(;i == 0;)
		{
			StarSystemAPI system = getSector().getStarSystem("Corvus");
			playerfleet = system.getEntityByName("Fleet");
			playercargo = playerfleet.getCargo();
			if(playercargo.removeItems(CargoAPI.CargoItemType.RESOURCES, "cout", 1)){
				Global.getSectorAPI().addMessage("来吧我的朋友，来看看有没有你喜欢的武器。");
			}

		}
	}
	public SectorAPI getSector() {
		return sector;
	}

//	@Override
//	public CampaignFleetAPI spawnFleet() 
//	{
	 //str1=StarfarerSettings.OO0000()+"/Fairy/data/hulls/ship_data.csv";
//	 return null;

//	}
	/*public boolean compareFileContentByChar(File f1 ,File f2) throws IOException{
        FileInputStream fis1 = new FileInputStream(f1);
        FileInputStream fis2 = new FileInputStream(f2);
        int b = 0;
        int c = 0;
        while(true){
            b = fis1.read();
            c = fis2.read();
            if(b!=-1 && c!=-1){
                if(b!=c){
                    return false;
                }
            }else{
                break;
            }
        }
        return true;
    }*/
	
}