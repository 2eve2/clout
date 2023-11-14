// global
import 'dart:ui';
import 'package:clout/hooks/apis/authorized_api.dart';
import 'package:clout/providers/campaign_register_controller.dart';
import 'package:clout/widgets/age_slider.dart';
import 'package:clout/screens/campaign_register/widgets/category_dropdown.dart';
import 'package:clout/widgets/input/input_elements/widgets/text_input.dart';
import 'package:clout/widgets/minimumfollowers_dialog.dart';
import 'package:clout/widgets/pay_dialog.dart';
import 'package:clout/screens/campaign_register/widgets/product_textinput.dart';
import 'package:clout/widgets/recruit_input.dart';
import 'package:clout/widgets/region_multiselect.dart';
import 'package:clout/screens/campaign_register/widgets/utils.dart';
import 'package:clout/widgets/buttons/big_button.dart';
import 'package:clout/widgets/signature.dart';
import 'package:clout/utilities/bouncing_listview.dart';
import 'package:clout/widgets/buttons/toggle_button.dart';
import 'package:clout/widgets/image_pickder/image_widget.dart';
import 'package:clout/widgets/sns/platform_toggle.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:clout/style.dart' as style;
import 'dart:ui' as ui;
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:get/get.dart';
import 'package:image_gallery_saver/image_gallery_saver.dart';
import 'package:syncfusion_flutter_signaturepad/signaturepad.dart';

// Screens
import 'package:clout/screens/campaign_register/widgets/data_title.dart';

// Widgets
import 'package:clout/widgets/header/header.dart';

class CampaignRegister extends StatelessWidget {
  CampaignRegister({super.key});

  final campaignRegisterController = Get.put(
    CampaignRegisterController(),
    tag: 'campaignRegister',
  );

  AuthorizedApi authorizedApi = AuthorizedApi();

  register() async {
    if (campaignRegisterController.category != null &&
        campaignRegisterController.campaignTitle != null &&
        campaignRegisterController.offeringItems != null &&
        campaignRegisterController.itemDetail != null) {
      await campaignRegisterController.setCampaign();
      var response = await authorizedApi.postRequest(
          '/advertisement-service/v1/advertisements',
          campaignRegisterController.campaign);
      await campaignRegisterController.printAll();
      print(response);
      if (response['statusCode'] == 201) {
        await handleSaveButtonPressed(); // 서명 갤러리 저장함수
        Get.offNamed('/home');
      }
    } else {
      Fluttertoast.showToast(msg: '필수값 입력');
    }
  }

  // 특정 요소에 접근할때 키로 접근하는듯
  final GlobalKey<SfSignaturePadState> signatureGlobalKey = GlobalKey();
  final GlobalKey stackGlobalKey = GlobalKey();

  Future<void> handleSaveButtonPressed() async {
    print("START CAPTURE");
    final renderObject = stackGlobalKey.currentContext!.findRenderObject();
    if (renderObject is RenderRepaintBoundary) {
      var boundary = renderObject;
      ui.Image image = await boundary.toImage(pixelRatio: 5.0);
      final byteData = await image.toByteData(format: ui.ImageByteFormat.png);
      if (byteData != null) {
        // 나중에 axios로 db에 넣을때 여기 if 안에서 넣어야 함
        campaignRegisterController.setSignature(byteData);
        await ImageGallerySaver.saveImage(byteData.buffer.asUint8List());
        Utils.toast('서명이 갤러리에 저장되었습니다.');
      }
    } else {
      print('여기로 왔는데?');
    }
  }

  @override
  Widget build(BuildContext context) {
    return GetBuilder<CampaignRegisterController>(
      tag: 'campaignRegister',
      builder: (controller) => Scaffold(
        appBar: PreferredSize(
            preferredSize: Size.fromHeight(70),
            child: Header(header: 4, headerTitle: '캠페인 작성')),
        body: Container(
          color: Colors.white,
          width: double.infinity,
          child: BouncingListview(
            child: FractionallySizedBox(
              widthFactor: 0.9,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  DataTitle(text: '카테고리'),
                  SizedBox(height: 10),
                  CategoryDropdown(
                      category: controller.category,
                      setCategory: controller.setCategory),
                  SizedBox(height: 20),
                  DataTitle(text: '캠페인 제목'),
                  SizedBox(height: 10),
                  TextInput(
                    setData: controller.setCampaignTitle,
                    placeholder: '캠페인 제목 입력',
                    maxLength: 50,
                    maxValue: -1,
                    minLines: 1,
                    maxLines: 1,
                  ),
                  SizedBox(height: 20),
                  DataTitle(text: '모집 인원(최대 100명)'),
                  RecruitInput(
                      recruitCount: controller.recruitCount,
                      setRecruitCount: controller.setRecruitCount),
                  SizedBox(height: 20),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      DataTitle(text: '제공 내용'),
                      Row(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          Text(
                            '배송 여부',
                            style: TextStyle(height: 1.2),
                          ),
                          SizedBox(width: 4),
                          ToggleButton(
                            parentPositive: controller.deliveryPositive,
                            setPositive: controller.setDeliveryPositive,
                            leftIcon: null,
                            rightIcon: Icons.local_shipping_outlined,
                          )
                        ],
                      ),
                    ],
                  ),
                  SizedBox(height: 10),
                  TextInput(
                    setData: controller.setOfferingItems,
                    placeholder: '제공 내용(제공할 물품 또는 서비스)',
                    maxLength: 500,
                    maxValue: -1,
                    minLines: 5,
                    maxLines: 5,
                  ),
                  SizedBox(height: 10),
                  SizedBox(
                    height: 40,
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Row(
                          children: [
                            Text(
                              '광고비 ',
                              style: style.textTheme.headlineMedium?.copyWith(
                                  fontWeight: FontWeight.w600, height: 1.2),
                            ),
                            ToggleButton(
                              parentPositive: controller.payPositive,
                              setPositive: controller.setPayPositive,
                              leftIcon: Icons.money_off_outlined,
                              rightIcon: Icons.attach_money_outlined,
                            ),
                          ],
                        ),
                        controller.payPositive
                            ? PayDialog(
                                title: '희망 광고비',
                                hintText: '희망 광고비 입력',
                                controllerTag: 'campaignRegister',
                              )
                            : Container(),
                      ],
                    ),
                  ),
                  SizedBox(height: 20),
                  DataTitle(text: '요구 사항'),
                  SizedBox(height: 10),
                  TextInput(
                    setData: controller.setItemDetail,
                    placeholder: '광고 게시 요구 사항',
                    maxLength: 800,
                    maxValue: -1,
                    minLines: 10,
                    maxLines: 10,
                  ),
                  SizedBox(height: 10),
                  DataTitle(text: '판매처 링크(선택사항)'),
                  SizedBox(height: 10),
                  TextInput(
                    setData: controller.setLink,
                    placeholder: '판매처 링크 입력',
                    maxLength: 300,
                    maxValue: -1,
                    minLines: 1,
                    maxLines: 1,
                  ),
                  SizedBox(height: 20),
                  DataTitle(text: '제품 사진 첨부(최대 4장)'),
                  SizedBox(height: 10),
                  ImageWidget(controllerTag: 'campaignRegister'),
                  SizedBox(height: 30),
                  DataTitle(text: '광고 희망 플랫폼'),
                  SizedBox(height: 10),
                  PlatformToggle(
                    multiAllowed: true,
                    controllerTag: 'campaignRegister',
                  ),
                  SizedBox(height: 30),
                  DataTitle(text: '희망 클라우터 나이'),
                  AgeSlider(controllerTag: 'campaignRegister'),
                  SizedBox(height: 30),
                  DataTitle(text: '희망 최소 팔로워 수'),
                  MinimumfollowersDialog(
                    controllerTag: 'campaignRegister',
                  ),
                  SizedBox(height: 10),
                  DataTitle(text: '지역 선택'),
                  SizedBox(height: 10),
                  RegionMultiSelect(
                    controllerTag: 'campaignRegister',
                  ),
                  SizedBox(height: 20),
                  Signature(
                      globalKey: stackGlobalKey,
                      signatureKey: signatureGlobalKey),
                  Padding(
                    padding: const EdgeInsets.only(top: 20, bottom: 20),
                    child: SizedBox(
                      width: double.infinity,
                      height: 50,
                      child: BigButton(
                        title: '캠페인 등록',
                        function: register,
                      ),
                    ),
                  )
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
