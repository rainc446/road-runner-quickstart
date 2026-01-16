package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Limelight;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

/**
 * Example OpMode demonstrating how to use Limelight Actions with RoadRunner
 * This demonstrates the primary use case: detecting the motif once at the start
 * and accessing it throughout the match.
 */
@Autonomous(name = "Limelight Action Example", group = "Testing")
public class LimelightActionExample extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware
        Pose2d beginPose = new Pose2d(new Vector2d(-60.0, 37), Math.toRadians(0));
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        Limelight limelight = new Limelight(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Detect motif once at the beginning and store it for the match
            Action detectMotifSequence = new SequentialAction(
                    limelight.startLimelightAction(100),
                    limelight.setPipelineAction(Limelight.Pipelines.APRILTAGGER),
                    limelight.detectArtifactSequenceAction(),
                    limelight.closeLimelightAction()
            );

            // Run the detection sequence
            com.acmerobotics.roadrunner.Actions.runBlocking(detectMotifSequence);

            // Now the motif is stored and can be accessed throughout the match
            Limelight.Motif detectedMotif = limelight.getStoredMotif();
            
            if (detectedMotif != null) {
                telemetry.addData("Detected Motif", detectedMotif.name());
                telemetry.addData("Motif ID", detectedMotif.getValue());
                
                // Example: Use the motif to make autonomous decisions
                switch (detectedMotif) {
                    case GPP:
                        telemetry.addData("Action", "Following GPP path");
                        // Execute GPP-specific autonomous routine
                        break;
                    case PGP:
                        telemetry.addData("Action", "Following PGP path");
                        // Execute PGP-specific autonomous routine
                        break;
                    case PPG:
                        telemetry.addData("Action", "Following PPG path");
                        // Execute PPG-specific autonomous routine
                        break;
                }
            } else {
                telemetry.addData("Warning", "No motif detected, using default path");
                // Execute default autonomous routine
            }
            
            telemetry.update();
            
            // Example: Access the stored motif later in the match
            sleep(1000);
            
            // You can check the motif again anytime during the match
            Limelight.Motif storedMotif = limelight.getStoredMotif();
            telemetry.addData("Stored Motif (later)", storedMotif != null ? storedMotif.name() : "None");
            telemetry.update();
            
            sleep(2000);
        }
    }
}
