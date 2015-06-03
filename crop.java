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
	
	public void grow(){
	    if (!isInfested()&&getWater()>=1){
	        setHeight(getHeight()+1);
	        setWater(getWater()-1);
	    }else if (isInfested()){
	        setHeight(getHeight()-1);
	        if (getHeight()==0){
	            setInfested(false);
	        }
	    }else{
	        
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
