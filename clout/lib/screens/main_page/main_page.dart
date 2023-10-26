// global
import 'package:flutter/material.dart';
import 'package:clout/style.dart' as style;
import 'package:get/get.dart';

// providers
import 'package:clout/providers/nav_bar_controller.dart';

// screens
import 'package:clout/screens/campaign_register/campaign_register.dart';
import 'package:clout/screens/home/home.dart';
import 'package:clout/screens/campaign_list/campaign_list.dart';
import 'package:clout/screens/chatting/chatting_list.dart';
import 'package:clout/screens/point/clouter_point_list.dart';

// widgets
import 'package:clout/widgets/nav_bar/nav_bar.dart';

class MainPage extends StatelessWidget {
  const MainPage({super.key});

  @override
  Widget build(BuildContext context) {
    Get.put(NavBarController());
    return Scaffold(
      body: GetBuilder<NavBarController>(builder: (controller) {
        if (controller.tab == 0) {
          return Home();
          // 아래 controll.tab에 따라 보여주는 화면 다르게 하기
        } else if (controller.tab == 1) {
          return CampaignList();
        } else if (controller.tab == 3) {
          return ChattingList();
        } else if (controller.tab == 4) {
          return ClouterPointList();
        }
        return Home();
      }),
      bottomNavigationBar: NavBar(),
    );
  }
}
