import 'package:clout/screens/join/find_numberVerify.dart';
import 'package:clout/screens/join/widgets/jobToggle.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:clout/utilities/bouncing_listview.dart';
import 'package:clout/widgets/input/input.dart';

import '../../style.dart' as style;

import 'package:clout/screens/join/widgets/big_button.dart';
import 'package:clout/screens/join/widgets/small_button.dart';
import 'package:clout/screens/join/numberVerify.dart';

import 'package:clout/screens/join/widgets/clouter/clouterJoin1.dart';
import 'package:clout/screens/join/widgets/clouter/clouterJoin2.dart';
import 'package:clout/screens/join/widgets/clouter/clouterJoin3.dart';

class FindPassword extends StatefulWidget {
  const FindPassword({super.key});

  @override
  _FindPasswordState createState() => _FindPasswordState();
}

class _FindPasswordState extends State<FindPassword> {

  var number;
  setNumber(input) {
    setState(() {
      number = input;
    });
  }



  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: EdgeInsets.only(left: 25, top: 100),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
              Text('비밀번호 찾고', style: style.textTheme.titleMedium),
              Row(
                children: <Widget>[
                  Text('CLOUT', style: style.textTheme.titleMedium?.copyWith(
                    color: style.colors['main1'])),
                  Text('와 함께', style: style.textTheme.titleMedium),
                ],
              ),
              Text('매칭해요', style: style.textTheme.titleMedium),
              SizedBox(height: 10),
              Padding(
              padding: const EdgeInsets.only(top: 20, right: 25, bottom: 20),
              child: Column(
                children: [
                  JobToggle(),
                  SizedBox(height: 20), 
                  Input(
                    placeholder: '전화번호 입력',
                    setText: setNumber,
                  ),
                  SizedBox(height: 40), 
                  BigButton(
                    title: '코드 전송', 
                    function: () {
                      Get.to(FindNumberVerify());
                    }
                  ),
                ],
              ),
            ),
              
            ],
          ),
        ),
      );
  }
}