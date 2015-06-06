/**
 * info1103 - assignment 5
 * Andrew Houghton
 * ahou8288
 */

public class Crop {
    private int water;
    private int height;
    private boolean infested;
    private boolean sprayed;
    
    //get and set
    public int getWater() {
		return water;
	}
	public void setWater(int water) {
		this.water = water;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
	    if (height<0){
	        this.height=0;
	    } else if (height>8){
	        this.height=8;
	    } else {
		    this.height = height;
	    }
	}
	public boolean isInfested() {
		return infested;
	}
	public void setInfested(boolean infested) {
		this.infested = infested;
	}
	public boolean isSprayed() {
		return sprayed;
	}
	public void setSprayed(boolean sprayed) {
		this.sprayed = sprayed;
	}
	
	public void clone(Farm newFarm,int i,int j){
	    newFarm.crops[i][j].setWater(getWater());
	    newFarm.crops[i][j].setHeight(getHeight());
	    newFarm.crops[i][j].setInfested(isInfested());
	    newFarm.crops[i][j].setSprayed(isSprayed());
	}
	
	
	private boolean adjInfested(Farm farm,int i, int j){
	    int h=farm.getHeight();
	    int w=farm.getWidth();
	    for (int k1=i-1;k1<i+2;k1++){
	        for (int k2=j-1;k2<j+2;k2++){
	            if (k1<w&&k1>=0&&k2<h&&k2>=0&&k1!=i&&k2!=j){//if it is in the farm
	                //System.out.printf("k1 %d k2 %d\n",k1,k2);
	                if (farm.crops[k1][k2].isInfested()){
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}
	
	public void grow(Farm newFarm,int i,int j){
	    if (adjInfested(newFarm,i,j)&&!newFarm.crops[i][j].isSprayed()&&(newFarm.crops[i][j].getHeight()!=0)){
	        setInfested(true);
	    }else if (!newFarm.crops[i][j].isInfested()&&newFarm.crops[i][j].getWater()>=1){
	        setHeight(newFarm.crops[i][j].getHeight()+1);
	        setWater(newFarm.crops[i][j].getWater()-1);
	    }else if (newFarm.crops[i][j].isInfested()){
	        setHeight(newFarm.crops[i][j].getHeight()-1);
	        if (newFarm.crops[i][j].getHeight()==0){
	            setInfested(false);
	        }
	    }
	}
    
    
    public Crop(){
        //Initialise a new crop
        setWater(0);
        setHeight(0);
        setInfested(false);
        setSprayed(false);
    }
}
