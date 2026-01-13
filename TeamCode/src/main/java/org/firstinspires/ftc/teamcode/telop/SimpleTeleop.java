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
            controllerBehaviorB();
        }

    }

    public void controllerBehaviorA () {
        double leftX = gamepad1.left_stick_x;
        double leftY = -gamepad1.left_stick_y;
        double rightX = gamepad1.right_stick_x ;

        if (leftY <= -0.1 && leftY >= 0.1) {
            leftY = 0;
        }
        if (leftX <= -0.1 && leftX >= 0.1) {
            leftX = 0;
        }
        if (rightX <= -0.1 && rightX >= 0.1) {
            rightX = 0;
        }

        driveTrain.driverRelativePower(leftY,leftX , rightX);



        if (gamepad1.x) { //go left

        }
        if (gamepad1.b) { //go right

        }
        if (gamepad1.a) { //go down

        }
        if (gamepad1.y) { //go up

        }
    }

    public void controllerBehaviorB () {
        if (gamepad2.xWasPressed()) {

        }

        if (gamepad2.bWasPressed()) {

        }

        if (gamepad2.dpad_up) {

        }
        if (gamepad2.dpad_down) {

        }
    }
}
