import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'package:clout/widgets/input/input.dart';
import '../../style.dart' as style;
import 'package:clout/screens/join/widgets/big_button.dart';
import 'package:clout/screens/join/widgets/small_button.dart';
import 'package:clout/screens/join/numberVerify.dart';

class AdvertiserJoin extends StatefulWidget {
  const AdvertiserJoin({super.key});

  @override
  State<AdvertiserJoin> createState() => _AdvertiserJoinState();
}

class _AdvertiserJoinState extends State<AdvertiserJoin> {
  var name;
  var phoneNumber;
  var email;
  var password;
  var checkPassword;
  var buisnessName;
  var buisnessNumber;
  var buisnessAddress;

  // 추가 변수와 함수
  var obscured = true; // 예시 값
  Icon suffixIcon = Icon(Icons.visibility); // 예시 값
  var doubleId = 1;

  setDoubleId() {
    setState((){
      //없을 때 0
      //중복이면 1
      //가능하면 2 
      //지금은 편의상 중복 아니라고 함
      doubleId = 2;
      print(doubleId);
    });
  }

  setName(input) {
    setState(() {
      name = input;
    });
  }

  setPhoneNumber(input) {
    setState(() {
      phoneNumber = input;
    });
  }

  setEmail(input) {
    setState(() {
      email = input;
    });
  }

  setPassword(input) {
    setState(() {
      password = input;
    });
  }

  setCheckPassword(input) {
    setState(() {
      checkPassword = input;
    });
  }

  setBuisnessName(input){
    setState((){
      buisnessName=input;
    });
  }

  setBuisnessNumber(input){
    setState((){
      buisnessNumber=input;
    });
  }

  setBuisnessAddress(input){
    setState((){
      buisnessAddress=input();
    });
  }

  setObscured() {
    setState(() {
      obscured = !obscured;
      print(obscured);
      if (obscured) {
        suffixIcon = Icon(Icons.visibility_outlined);
      } else {
        suffixIcon = Icon(Icons.visibility_off_outlined);
      }
    });
  }

  @override
  Widget build(BuildContext context) {
      return Scaffold(
        body: SingleChildScrollView(
          child: Padding(
            padding: EdgeInsets.only(left: 25, top: 15, right:25, bottom:15),
            child: ListView(
              shrinkWrap: true,
              children: <Widget>[
                Text('가입하고', style: style.textTheme.titleMedium),
                Row(
                  children: <Widget>[
                    Text('CLOUT', style: style.textTheme.titleMedium?.copyWith(
                      color: style.colors['main1'])),
                    Text('와 함께', style: style.textTheme.titleMedium),
                  ],
                ),
                Text('매칭해요', style: style.textTheme.titleMedium),
                Input(
                  placeholder: '담당자명 입력',
                  setText: setName,
                ),
                Stack(
                  children: [
                    Input(
                      placeholder: '전화번호 입력',
                      setText: setPhoneNumber,
                    ),
                    Positioned(
                      right: 10,
                      top: 22,
                      child: SmallButton(
                        title: '인증',
                        function: () {
                          Get.to(() => NumberVerify());
                        },
                      ),
                    ),
                  ],
                ),
                Stack(
                  children: [
                    Input(
                      placeholder: '아이디 입력',
                      setText: setEmail,
                    ),
                    Positioned(
                      right: 10,
                      top: 22,
                      child: SmallButton(
                        title: '중복 확인',
                        function: setDoubleId,
                      ),
                    ),
                  ],
                ),
                SizedBox(
                  height: 25,
                  child: TextButton(
                    style: TextButton.styleFrom(
                      minimumSize: Size.zero,
                      padding: EdgeInsets.zero,
                      tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                      alignment: Alignment.centerRight,
                    ),
                    onPressed: () {
                      // 원하는 동작을 구현
                    },
                    child: doubleId == 1
                        ? Text(
                            '아이디 중복 확인이 필요해요',
                            style: style.textTheme.bodyMedium?.copyWith(
                              color: style.colors['gray'],
                              height: 2,
                            ),
                          )
                        : doubleId == 2
                            ? Text(
                                '사용 가능한 아이디입니다',
                                style: style.textTheme.bodyMedium?.copyWith(
                                  color: style.colors['main1'],
                                  height: 2,
                                ),
                              )
                            : Text(
                                '이미 사용 중인 아이디입니다',
                                style: style.textTheme.bodyMedium?.copyWith(
                                  color: style.colors['Darkgray'],
                                  height: 2,
                                ),
                              ),
                  ),
                ),
                Input(
                  placeholder: '패스워드 입력',
                  setText: setPassword,
                  obscure: obscured,
                  suffixIcon: suffixIcon,
                  setObscured: setObscured,
                ),
                Input(
                  placeholder: '패스워드 확인',
                  setText: setCheckPassword,
                  obscure: obscured,
                  suffixIcon: suffixIcon,
                ),
                Input(
                  placeholder: '업체명 입력',
                  setText: setBuisnessName,
                ),
                Input(
                  placeholder: '사업자등록번호 입력',
                  setText: setBuisnessNumber,
                ),
                Input(
                  placeholder: '업체 주소 입력',
                  setText: setBuisnessAddress,
                ),
                Padding(
                  padding: const EdgeInsets.only(top: 20),
                  child: BigButton(
                    title: '회원가입',
                    function: () {
                      Get.toNamed('/login');
                    },
                  ),
                )
              ],
            ),
          ),
        ),
      );
    }
  }
