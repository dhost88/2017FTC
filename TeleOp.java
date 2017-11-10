package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.vuforia.State;

/**
 * Created by ssharma on 10/20/17.
 */
@TeleOp(name="SimpleFtcControler", group= "Linear OpMode")
public class cameraMove extends LinearOpMode {

    private DcMotor motorLeftF;
    private DcMotor motorRightF;
    private DcMotor motorLeftB;
    private DcMotor motorRightB;
    private Servo armServoL;
    private Servo armServoR;
    private DcMotor Carry;
    private ColorSensor colorDetect;
    private int movingSpeed=0;


    //private Servo armServo;
    //RC-03 & DS-03
    //private static final double ARM_RETRACTED_POSITION = 0.2;
    //private static final double ARM_EXTENDED_POSITION = 0.8;

    @Override
    public void runOpMode() throws InterruptedException {




        motorLeftF = hardwareMap.dcMotor.get("motorLeft");
        motorRightF = hardwareMap.dcMotor.get("motorRight");
        motorLeftB = hardwareMap.dcMotor.get("motorLeft");
        motorRightB = hardwareMap.dcMotor.get("motorRight");
        armServoL=hardwareMap.servo.get("armLeft");
        armServoR=hardwareMap.servo.get("armRight");
        Carry=hardwareMap.dcMotor.get("carry");
        //colorDetect=hardwareMap.colorSensor.get("color");


        motorLeftB.setDirection(DcMotor.Direction.REVERSE);
        motorRightB.setDirection(DcMotor.Direction.REVERSE);
        //armServoR.setDirection(Servo.Direction.REVERSE);



        //armServo = hardwareMap.servo.get("armServo");
        // armServo.setPosition(ARM_RETRACTED_POSITION);

        waitForStart();

        while(opModeIsActive())

        {
            /*
            telemetry.addData("Color Detect Green: ",colorDetect.green());
            telemetry.addData("Color Detect Red: ",colorDetect.red());
            telemetry.addData("Color Detect Blue: ",colorDetect.blue());
            telemetry.addData("Speed Level right now: ", Integer.toString((1-movingSpeed)*100)+"%");
            */
            if(gamepad1.a)
            {
                armServoL.setPosition(0);
                armServoL.setPosition(1);

            }
            if (gamepad1.x){

                if (gamepad1.dpad_left){
                    motorLeftF.setPower(-0.2);
                    motorRightF.setPower(0.2);
                    motorLeftB.setPower(0.2);
                    motorRightB.setPower(-0.2);

                }
                if (gamepad1.dpad_right){
                    motorLeftF.setPower(0.2);
                    motorRightF.setPower(-0.2);
                    motorLeftB.setPower(-0.2);
                    motorRightB.setPower(0.2);

                }

            }else{

                if (gamepad1.dpad_right){
                    if (movingSpeed>0){
                        movingSpeed-=0.1;

                    }

                }
                if (gamepad1.dpad_left){
                    if (movingSpeed<1){
                        movingSpeed+=0.1;

                    }

                }

            }
            if(gamepad1.y){
                movingSpeed=0;

            }
            if(gamepad1.b)
            {
                armServoR.setPosition(0.5);
                armServoL.setPosition(0.5);
            }
            if(gamepad1.dpad_up){
                Carry.setPower(0.3);


            }
            if (gamepad1.dpad_down){
                Carry.setPower(-0.3);

            }



            if (gamepad1.left_stick_y<-0.2 & gamepad1.left_stick_y>0.2 & gamepad1.left_stick_x<-0.2 & gamepad1.left_stick_x>0.2){
                if (Math.abs(gamepad1.left_stick_y)>Math.abs(gamepad1.left_stick_x)){
                    float giveGo=gamepad1.left_stick_y;
                    if (gamepad1.left_stick_y>0){
                        giveGo-=movingSpeed;
                    }else if(gamepad1.left_stick_y<0){
                        giveGo+=movingSpeed;
                    }
                    motorLeftF.setPower(giveGo);
                    motorRightF.setPower(giveGo);
                    motorLeftB.setPower(giveGo);
                    motorRightB.setPower(giveGo);


                }else{

                    float giveGo=gamepad1.left_stick_x;
                    motorLeftF.setPower(-giveGo);
                    motorRightF.setPower(giveGo);
                    motorLeftB.setPower(-giveGo);
                    motorRightB.setPower(giveGo);


                }

            }


            if (gamepad1.right_stick_x>0.2 & gamepad1.right_stick_x<-0.2){
                float giveGo=gamepad1.right_stick_x;
                if(giveGo<0){
                    giveGo=-giveGo;
                }
                if (giveGo>0){
                    giveGo=Math.abs(giveGo)-movingSpeed;
                }else{
                    giveGo=Math.abs(giveGo)-movingSpeed;
                    giveGo=-giveGo;

                }
                motorLeftF.setPower(giveGo);
                motorRightF.setPower(-giveGo);
                motorLeftB.setPower(-giveGo);
                motorRightB.setPower(giveGo);

            }




        }
        idle();
    }
}
