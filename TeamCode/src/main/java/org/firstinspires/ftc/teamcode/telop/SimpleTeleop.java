package org.firstinspires.ftc.teamcode.telop;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;


@TeleOp
public class SimpleTeleop extends LinearOpMode {

    private MecanumDrive driveTrain;

    private Pose2d intialPosition;

    private Telemetry telemetry;


    @Override
    public void runOpMode() throws InterruptedException {

        intialPosition = new Pose2d(0,0,0);


        driveTrain = new MecanumDrive(this.hardwareMap, intialPosition);

        waitForStart();
        while (opModeIsActive()) {
            controllerBehaviorA();

        }

    }

    public void controllerBehaviorA () {
        if (gamepad1.x) { //go left
        }
        if (gamepad1.b) { //go right

        }
        if (gamepad1.a) { //go down

        }
        if (gamepad1.y) { //go up

        }
    }
}
