package coordinatedisplay;

public class CoordinateState {
    public double x, y, z;
    public boolean toggle = true;
    public double orientation;

    public String orientationString(double yaw) {
        orientation = calculateOrientation(yaw);
        if (orientation == 0) {
            return "S (0,-)";
        } else if (orientation == 90) {
            return "W (-,0)";
        } else if (orientation == 180) {
            return "N (0,+)";
        } else if (orientation == 270) {
            return "E (+,0)";
        } else {
            return "?";
        }
    }

    public double calculateOrientation(double playerYaw) {
        // Normalize playerYaw to be within [0, 360)
        playerYaw = playerYaw % 360;
        if (playerYaw < 0) {
            playerYaw += 360;
        }

        // Calculate orientation based on playerYaw
        if (playerYaw >= 315 || playerYaw < 45) {
            return 0; // South
        } else if (playerYaw >= 45 && playerYaw < 135) {
            return 90; // West
        } else if (playerYaw >= 135 && playerYaw < 225) {
            return 180; // North
        } else { // playerYaw >= 225 && playerYaw < 315
            return 270; // East
        }
    }

}
