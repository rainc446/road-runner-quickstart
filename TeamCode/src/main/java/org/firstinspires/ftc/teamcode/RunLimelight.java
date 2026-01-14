package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLResult;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp(name = "RunLimelight", group = "Testing")
public class RunLimelight extends LinearOpMode {

    private Limelight limelight;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize Limelight with the opmode hardwareMap
        limelight = new Limelight(hardwareMap);

        // Start the limelight with a default polling rate
        limelight.startLimelight(60);

        // Set a default pipeline to view (AprilTag detector by default)
        limelight.setPipeline(Limelight.Pipelines.APRILTAGGER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Read the latest LLResult and print fiducial (AprilTag) info if present
            LLResult result = limelight.getLatestResult();
            if (result == null || !result.isValid()) {
                telemetry.addData("Limelight", "No valid target found");
            } else {
                telemetry.addData("Limelight", "Valid target found");

                // General fields
                telemetry.addData("tx", result.getTx());
                telemetry.addData("ty", result.getTy());

                Pose3D botpose = result.getBotpose();
                if (botpose != null) telemetry.addData("Botpose", botpose.toString());

                // Fiducial results (AprilTags)
                java.util.List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
                if (fiducials != null && !fiducials.isEmpty()) {
                    for (LLResultTypes.FiducialResult fr : fiducials) {
                        telemetry.addData("Fiducial", String.format("ID: %d, Family: %s, X: %.2f, Y: %.2f", fr.getFiducialId(), fr.getFamily(), fr.getTargetXDegrees(), fr.getTargetYDegrees()));
                    }
                } else {
                    telemetry.addData("Fiducial", "None");
                }
            }

            // Always flush telemetry
            telemetry.update();

            // Sleep a bit to avoid spamming CPU (polling rate is handled by Limelight object)
            sleep(50);
        }

        // Stop the limelight when exiting
        limelight.closeLimeLight();
    }
}
