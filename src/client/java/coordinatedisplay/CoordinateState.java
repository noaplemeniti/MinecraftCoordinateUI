package coordinatedisplay;

public class CoordinateState {
    public double x, y, z;
    public boolean toggle = true;
    public double orientation;
    public String orientationx;
    public String orientationz;
    

    public String orientationString(double yaw) {
        orientation = calculateOrientation(yaw);
        if (orientation == 0) {
            return "S";
        } else if (orientation == 90) {
            return "E";
        } else if (orientation == 180) {
            return "N";
        } else if (orientation == 270) {
            return "W";
        } else if (orientation == 45) {
            return "SE";
        } else if (orientation == 135) {
            return "NE";
        } else if (orientation == 225) {
            return "NW";
        } else if (orientation == 315) {
            return "SW";
        } else {
            return "?";
        }
    }

    public void setOrientationStrings(double yaw){
        orientation = calculateOrientation(yaw);
        if (orientation == 0) {
            orientationx = "";
            orientationz = "+";
        } else if (orientation == 90) {
            orientationx = "+";
            orientationz = "";
        } else if (orientation == 180) {
            orientationx = "";
            orientationz = "-";
        } else if (orientation == 270) {
            orientationx = "-";
            orientationz = "";
        } else if (orientation == 45) {
            orientationx = "+";
            orientationz = "+";
        } else if (orientation == 135) {
            orientationx = "+";
            orientationz = "-";
        } else if (orientation == 225) {
            orientationx = "-";
            orientationz = "-";
        } else if (orientation == 315) {
            orientationx = "-";
            orientationz = "+";
        } else {
            orientationx = "?";
            orientationz = "?";
        }
    }

    public double calculateOrientation(double playerYaw) {
        // Normalize playerYaw to be within [0, 360)
        playerYaw = playerYaw % 360;
        if (playerYaw < 0) {
            playerYaw += 360;
        }

        if (playerYaw >=337.5 || playerYaw < 22.5) {
            return 0; // South
        } else if (playerYaw >= 22.5 && playerYaw < 67.5) {
            return 315; // South-West
        } else if (playerYaw >= 67.5 && playerYaw < 112.5) {
            return 270; // West
        } else if (playerYaw >= 112.5 && playerYaw < 157.5) {
            return 225; // North-West
        } else if (playerYaw >= 157.5 && playerYaw < 202.5) {
            return 180; // North
        } else if (playerYaw >= 202.5 && playerYaw < 247.5) {
            return 135; // North-East
        } else if (playerYaw >= 247.5 && playerYaw < 292.5) {
            return 90; // East
        } else { // playerYaw >= 292.5 && playerYaw < 337.5
            return 45; // South-East
        }
    }

}
