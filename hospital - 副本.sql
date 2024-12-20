/*
Navicat MySQL Data Transfer

Source Server         : LocalHost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : bs-hospital

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2024-09-19 22:56:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_booking
-- ----------------------------
DROP TABLE IF EXISTS `app_booking`;
CREATE TABLE `app_booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) NOT NULL COMMENT '预约编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `doctor_id` bigint(20) NOT NULL COMMENT '医生ID',
  `department_id` bigint(20) NOT NULL COMMENT '科室ID',
  `medical_card_id` bigint(20) NOT NULL COMMENT '就诊卡ID',
  `medical_card_sn` varchar(255) DEFAULT NULL COMMENT '就诊卡号',
  `medical_card_name` varchar(255) NOT NULL COMMENT '就诊人名称',
  `schedule_id` bigint(20) NOT NULL COMMENT '排班ID',
  `schedule_date` date NOT NULL COMMENT '预约时间',
  `schedule_moment` varchar(255) NOT NULL COMMENT '上午或下午',
  `booking_fee` decimal(10,2) NOT NULL COMMENT '挂号费',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '预约状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='预约挂号表';


-- ----------------------------
-- Table structure for app_department
-- ----------------------------
DROP TABLE IF EXISTS `app_department`;
CREATE TABLE `app_department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL COMMENT '上级科室',
  `name` varchar(100) NOT NULL COMMENT '科室名称',
  `introduce` varchar(255) NOT NULL COMMENT '科室介绍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COMMENT='科室表';

-- ----------------------------
-- Records of app_department
-- ----------------------------
INSERT INTO `app_department` VALUES ('1', '0', '手术科室', '手术科室是指通过手术来治疗疾病的科室，一般属于外科系统。大体上包括普通外科、心胸外科、肝胆外科、泌尿外科、神经外科、整形外科、显微外科等，这些大的手术科室随着学科精细化发展，进一步分为若干专科科室。', '2024-09-11 23:35:13');
INSERT INTO `app_department` VALUES ('2', '1', '骨科', '骨科是医院中专门处理骨骼、关节及其周围肌肉、韧带等软组织疾病的科室。我们提供骨折、脱位、关节炎、骨质疏松等多种疾病的诊断和治疗服务。采用先进的手术和非手术疗法，致力于减轻患者疼痛，恢复关节功能，提高生活质量。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('3', '1', '妇产科', '妇产科是专注于女性生殖系统健康和孕期护理的科室。我们提供妇科常规检查、孕期监护、分娩服务以及妇科疾病的诊断和治疗。科室拥有经验丰富的医生和护理团队，致力于提供全面、温馨的医疗服务，确保母婴安全。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('4', '1', '乳腺外科', '乳腺外科致力于乳腺疾病的预防、诊断和治疗。我们提供乳腺检查、乳腺癌筛查、乳腺肿瘤切除等服务。科室采用微创手术技术，减少患者痛苦，提高治愈率。同时，我们重视患者心理关怀，提供全程跟踪服务，帮助患者重拾信心。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('5', '1', '眼科', '眼科是专注于眼部健康和视力保护的科室。我们提供全面的眼部检查、视力矫正、白内障手术等服务。科室引进先进的眼科设备，采用精准的手术技术，致力于恢复和保护患者的视力。同时，我们提供眼保健知识教育，帮助患者预防眼部疾病。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('6', '1', '耳鼻喉头颈外科', '耳鼻喉头颈外科是专门处理耳、鼻、喉及头颈部疾病的科室。我们提供听力障碍、鼻窦炎、咽喉疾病和头颈部肿瘤的诊断与治疗。科室拥有专业的医疗团队和先进的治疗设备，采用微创手术和综合治疗方法，致力于减轻患者痛苦，提高生活质量。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('7', '1', '泌尿外科', '泌尿外科专注于泌尿系统疾病的诊断和治疗。我们提供前列腺疾病、泌尿系结石、泌尿系肿瘤等疾病的治疗。科室采用内镜技术、腹腔镜手术等先进手段，力求以最小的创伤达到最佳的治疗效果。同时，我们注重患者教育，帮助患者了解疾病，提高自我管理能力。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('8', '1', '肝胆胰外科', '肝胆胰外科是专门处理肝脏、胆囊、胰腺等消化系统疾病的科室。我们提供肝癌、胆囊炎、胰腺炎等疾病的手术治疗。科室拥有一流的医疗团队和设备，采用精准的手术技术，致力于提高手术成功率，减少术后并发症。同时，我们提供术后康复指导，帮助患者快速恢复。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('9', '1', '神经外科', '神经外科是专门处理神经系统疾病的科室。我们提供脑肿瘤、脑血管疾病、脊髓疾病的手术治疗。科室采用显微手术技术、神经导航系统等先进手段，力求手术精准、安全。同时，我们提供多学科综合治疗，包括康复训练和心理支持，帮助患者全面康复。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('10', '1', '胃肠外科', '胃肠外科专注于胃肠道疾病的诊断和治疗。我们提供胃癌、肠癌、胃溃疡等疾病的手术治疗。科室采用腹腔镜手术、内镜治疗等微创技术，减少患者创伤，加快康复。同时，我们提供营养指导和生活方式改善建议，帮助患者预防疾病复发。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('15', '1', '麻醉手术中心', '麻醉手术中心提供各类手术的麻醉服务。我们拥有经验丰富的麻醉医生和先进的麻醉设备，确保手术过程中患者的安全和舒适。同时，我们提供术后疼痛管理，帮助患者缓解术后疼痛。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('17', '1', '小儿外科', '小儿外科是专门为儿童提供外科治疗的科室。我们提供儿童肿瘤、先天性畸形、外伤等疾病的手术治疗。科室采用儿童友好的治疗环境和先进的手术技术，确保手术安全和效果。同时，我们提供儿童心理支持和家庭指导，帮助患者和家庭应对疾病。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('18', '1', '胸外科', '胸外科专注于胸部疾病的诊断和治疗。我们提供肺癌、食管癌、胸壁疾病等胸部肿瘤的手术治疗。科室采用胸腔镜手术、机器人辅助手术等微创技术，减少患者创伤，提高手术效果。同时，我们提供肺癌筛查和胸部疾病预防教育。', '2024-09-11 23:38:08');
INSERT INTO `app_department` VALUES ('24', '0', '非手术科室', '这类科室主要提供非手术治疗，包括内科（如呼吸内科、消化内科、神经内科、心血管内科等）、儿科、妇产科、精神心理科等。这些科室通常侧重于药物治疗、康复治疗和心理治疗等。', '2024-09-11 23:39:01');
INSERT INTO `app_department` VALUES ('25', '0', '医技科室', '医技科室主要提供医疗技术和检查服务，包括医学影像科（如放射科、超声科、核医学科）、检验科、病理科、康复科等。这些科室为临床诊断和治疗提供技术支持。', '2024-09-11 23:41:49');
INSERT INTO `app_department` VALUES ('26', '0', '中心及其他', '这类科室可能包括急诊科、重症监护科（ICU）、血液透析中心、烧伤科、肿瘤科等，它们通常专注于特定类型的疾病或患者群体。', '2024-09-11 23:42:11');
INSERT INTO `app_department` VALUES ('27', '24', '儿科', '儿科是专门针对儿童疾病进行诊断和治疗的科室。我们提供全面的儿童健康服务，包括儿童常见病、多发病的诊治，以及儿童预防保健、生长发育监测等。科室拥有专业的儿科医生团队，致力于提供温馨、细致的医疗服务，确保儿童的健康和安全。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('28', '24', '感染科', '感染科专注于感染性疾病的诊断和治疗。我们提供病毒性、细菌性、寄生虫性等各类感染疾病的专业治疗。科室配备有先进的检测设备，能够快速准确地诊断病原体，制定个性化的治疗方案。我们的目标是通过科学的治疗方法，帮助患者尽快恢复健康。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('29', '24', '风湿免疫科', '风湿免疫科致力于风湿性疾病和免疫系统疾病的诊断与治疗。我们提供类风湿关节炎、系统性红斑狼疮、强直性脊柱炎等疾病的治疗。科室采用药物治疗、物理治疗和康复治疗等多种手段，力求减轻患者症状，改善生活质量。同时，我们提供患者教育，帮助患者了解疾病，提高自我管理能力。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('30', '24', '内分泌科', '内分泌科专注于内分泌和代谢疾病的诊断与治疗。我们提供糖尿病、甲状腺疾病、肾上腺疾病等内分泌疾病的治疗。科室拥有专业的内分泌医生和营养师团队，提供个性化的饮食和生活方式指导，帮助患者控制病情，预防并发症。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('31', '24', '神经内科', '神经内科是专门处理神经系统疾病的科室。我们提供脑血管疾病、癫痫、帕金森病、多发性硬化等疾病的诊断和治疗。科室采用先进的神经影像技术和电生理检查，确保诊断的准确性。我们的目标是通过药物治疗、康复训练等综合治疗手段，帮助患者改善症状，提高生活质量。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('32', '24', '呼吸与危重症医学科', '呼吸与危重症医学科专注于呼吸系统疾病的诊断和治疗，同时提供危重症患者的监护和治疗。我们提供慢性阻塞性肺疾病、哮喘、肺炎、呼吸衰竭等疾病的治疗。科室配备有先进的呼吸支持设备和监护系统，确保患者安全。我们的目标是通过科学的治疗方法，帮助患者改善呼吸功能，提高生存质量。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('33', '24', '心血管内科', '心血管内科是专门处理心脏和血管疾病的科室。我们提供高血压、冠心病、心律失常、心力衰竭等疾病的诊断和治疗。科室采用心脏导管术、心脏起搏器植入等先进技术，结合药物治疗，致力于提高患者的心脏功能和生活质量。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('34', '24', '心肺血管病科', '心肺血管病科专注于心脏、肺部和血管系统的疾病。我们提供肺动脉高压、肺心病、心源性休克等疾病的诊断和治疗。科室采用心肺功能测试、血管造影等先进检查手段，确保诊断的准确性。我们的目标是通过综合治疗，改善患者的心肺功能，提高生存率。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('35', '24', '肾内科', '肾内科是专门处理肾脏疾病的科室。我们提供急性肾炎、慢性肾炎、肾病综合症、肾功能衰竭等疾病的诊断和治疗。科室采用血液透析、腹膜透析等肾脏替代治疗，结合药物治疗，致力于延缓肾功能的恶化，改善患者的生活质量。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('36', '24', '消化内科', '消化内科专注于消化系统疾病的诊断和治疗。我们提供胃炎、胃溃疡、肠梗阻、肝炎、肝硬化等疾病的治疗。科室采用胃镜、结肠镜等内镜技术，结合病理检查，确保诊断的准确性。我们的目标是通过药物治疗、饮食调整等综合治疗手段，帮助患者改善消化功能，提高生活质量。', '2024-09-11 23:42:30');
INSERT INTO `app_department` VALUES ('37', '25', '药学部', '药学部负责医院药品的采购、储存、分发和管理。我们提供临床用药咨询、药物监测和药物信息服务，确保患者用药安全有效。药学部与医疗团队紧密合作，参与药物治疗方案的制定和药品不良反应的监测，致力于提升药物治疗质量。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('38', '25', '核医学科', '核医学科是利用放射性核素进行诊断和治疗的科室。我们提供肿瘤、心脏和神经系统等疾病的核素显像诊断，以及甲状腺疾病和肿瘤的放射性核素治疗。科室配备有先进的核医学设备和专业的技术人员，确保检查和治疗的准确性和安全性。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('39', '25', '临床医学检验中心', '临床医学检验中心负责医院的临床检验工作，包括血液、尿液、生化、免疫等各类检验。我们采用先进的检验设备和严格的质量控制体系，确保检验结果的准确性和可靠性。检验中心为临床诊断和治疗提供科学依据，支持医院的医疗服务质量。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('40', '25', '超声医学科', '超声医学科利用超声波技术进行无创性的医学诊断。我们提供腹部、心血管、妇产、浅表器官等部位的超声检查，为临床提供实时、动态的影像信息。科室拥有先进的超声设备和经验丰富的超声医生，确保检查结果的准确性和及时性。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('41', '25', '心血管超声及心功能科', '心血管超声及心功能科专注于心脏和血管系统的超声检查。我们提供心脏结构、功能、血流等全面的超声评估，为心脏病的诊断和治疗提供重要信息。科室采用先进的心脏超声设备和技术，结合心功能检测，为患者提供精准的心脏健康评估。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('42', '25', '放射科', '放射科负责医院的影像诊断和介入治疗工作。我们提供X光、CT、MRI等影像检查，以及肿瘤、血管等疾病的介入治疗。科室配备有先进的影像设备和专业的放射医生，确保检查和治疗的准确性和安全性。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('43', '25', '输血医学科', '输血医学科负责医院的临床用血工作，包括血液的采集、检测、储存、分发和输血治疗。我们提供安全、有效的输血服务，确保患者输血安全。科室与血液中心紧密合作，参与血液资源的管理和血液安全的质量控制。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('44', '25', '临床营养科', '临床营养科负责医院的营养治疗和营养咨询工作。我们为患者提供个性化的营养评估、饮食指导和营养治疗，帮助患者改善营养状况，促进康复。科室与医疗团队合作，参与患者的营养管理，提高治疗效果和生活质量。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('45', '25', '病案统计科', '病案统计科负责医院病案的收集、整理、存储和统计分析工作。我们提供病案信息的查询、复印等服务，支持医院的医疗、教学和科研工作。科室采用电子病历系统，确保病案信息的完整性和安全性。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('46', '25', '病理科', '病理科负责医院的病理诊断工作，包括组织病理、细胞病理和分子病理等。我们提供肿瘤、感染、炎症等病变的病理诊断，为临床治疗提供重要依据。科室拥有先进的病理设备和专业的病理医生，确保诊断的准确性和及时性。', '2024-09-11 23:44:25');
INSERT INTO `app_department` VALUES ('47', '26', '急诊医学科（急救中心）', '急诊医学科（急救中心）是医院中负责处理紧急医疗情况的科室。我们提供24小时的急救服务，包括创伤、中毒、急性疾病等的紧急救治。科室配备有先进的急救设备和专业的急救团队，确保在关键时刻为患者提供快速、高效的医疗服务。', '2024-09-11 23:44:51');
INSERT INTO `app_department` VALUES ('48', '26', '重症医学中心', '重症医学中心是专门负责危重患者监护和治疗的科室。我们提供全面的重症监护服务，包括呼吸支持、循环支持、营养支持等。科室拥有先进的监护设备和专业的重症医护团队，致力于提高危重患者的救治成功率和生存质量。', '2024-09-11 23:44:51');
INSERT INTO `app_department` VALUES ('49', '26', '皮肤病性病研究所（皮肤科）', '皮肤病性病研究所（皮肤科）是专门研究和治疗皮肤性病的科室。我们提供各类皮肤病和性传播疾病的诊断与治疗服务。科室采用先进的皮肤检测技术，结合药物治疗、物理治疗等多种手段，致力于改善患者的皮肤健康，提高生活质量。', '2024-09-11 23:44:51');
INSERT INTO `app_department` VALUES ('50', '26', '医院感染控制中心', '医院感染控制中心负责医院内部的感染预防和控制工作。我们提供医院感染监测、感染预防指导、感染暴发调查等服务。科室与医疗团队合作，实施有效的感染控制措施，降低医院感染率，保障患者和医护人员的健康安全。', '2024-09-11 23:44:51');
INSERT INTO `app_department` VALUES ('51', '26', '健康管理中心', '健康管理中心是提供健康体检和健康管理服务的科室。我们为个人和团体提供全面的体检服务，包括基础体检、专项体检、健康评估等。科室拥有先进的体检设备和专业的健康管理团队，帮助客户了解自身健康状况，制定个性化的健康改善计划。', '2024-09-11 23:44:51');
INSERT INTO `app_department` VALUES ('52', '26', '辅助生殖医学中心', '辅助生殖医学中心是专门提供生殖健康和辅助生殖技术服务的科室。我们提供不孕不育诊断、人工授精、体外受精等辅助生殖技术。科室拥有先进的生殖医学实验室和专业的生殖医学团队，致力于帮助不孕不育患者实现生育梦想。', '2024-09-11 23:44:51');

-- ----------------------------
-- Table structure for app_doctor
-- ----------------------------
DROP TABLE IF EXISTS `app_doctor`;
CREATE TABLE `app_doctor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '医生姓名',
  `avatar` varchar(255) NOT NULL COMMENT '头像',
  `department_id` bigint(20) NOT NULL COMMENT '所属科室ID',
  `position` varchar(255) NOT NULL COMMENT '职称',
  `booking_fee` decimal(10,2) NOT NULL COMMENT '挂号费',
  `introduce` varchar(255) NOT NULL COMMENT '介绍',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='医生表';

-- ----------------------------
-- Records of app_doctor
-- ----------------------------
INSERT INTO `app_doctor` VALUES ('1', '刘奕', 'http://localhost:5173/images/1.jpg', '46', '主任医师', '20.00', '擅长儿童和青少年的牙体硬组织疾病和牙髓根尖周病的诊断和治疗，年轻恒牙外伤后的诊治和修复等。', '2024-09-11 23:48:23');
INSERT INTO `app_doctor` VALUES ('2', '李明', 'http://localhost:5173/images/1.jpg', '2', '主任医师', '20.00', '拥有丰富的临床经验，擅长内科常见病和多发病的诊治。', '2024-09-11 23:52:20');
INSERT INTO `app_doctor` VALUES ('3', '王丽', 'http://localhost:5173/images/2.jpg', '2', '副主任医师', '15.00', '专注于心脏疾病治疗，尤其在介入心脏病学方面有深入研究。xxxxx', '2024-09-11 23:52:20');
INSERT INTO `app_doctor` VALUES ('4', '张强', 'http://localhost:5173/images/3.jpg', '3', '主治医师', '7.00', '擅长各类外科手术，特别是在微创手术领域有显著成就。', '2024-09-11 23:52:20');
INSERT INTO `app_doctor` VALUES ('5', '赵红', 'http://localhost:5173/images/4.jpg', '4', '住院医师', '7.00', '对儿科疾病有深刻理解，擅长儿童常见疾病的诊治。', '2024-09-11 23:52:20');
INSERT INTO `app_doctor` VALUES ('6', '孙伟', 'http://localhost:5173/images/5.jpg', '5', '副主任医师', '15.00', '在肿瘤科领域有丰富的治疗经验，特别是在化疗和放疗方面。', '2024-09-11 23:52:20');
INSERT INTO `app_doctor` VALUES ('7', '钱晓', 'http://localhost:5173/images/6.jpg', '6', '主治医师', '7.00', '擅长妇科各类疾病的诊断和治疗，包括妇科炎症和肿瘤。', '2024-09-11 23:52:20');
INSERT INTO `app_doctor` VALUES ('8', '周杰', 'http://localhost:5173/images/7.jpg', '7', '主任医师', '20.00', '在神经外科领域有多年工作经验，擅长脑肿瘤和脑血管疾病的治疗。', '2024-09-11 23:52:20');
INSERT INTO `app_doctor` VALUES ('9', '吴兰', 'http://localhost:5173/images/8.jpg', '8', '副主任医师', '15.00', '专注于眼科疾病的治疗，尤其在白内障和青光眼手术方面有专长。', '2024-09-11 23:52:20');
INSERT INTO `app_doctor` VALUES ('10', '郑华', 'http://localhost:5173/images/9.jpg', '9', '主治医师', '7.00', '对口腔疾病的治疗有丰富经验，擅长牙齿矫正和种植。', '2024-09-11 23:52:20');

-- ----------------------------
-- Table structure for app_medical_card
-- ----------------------------
DROP TABLE IF EXISTS `app_medical_card`;
CREATE TABLE `app_medical_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `sn` varchar(255) NOT NULL COMMENT '卡号',
  `real_name` varchar(255) NOT NULL COMMENT '真实名字',
  `card_number` varchar(50) NOT NULL COMMENT '身份证',
  `phone` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL COMMENT '性别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='就诊卡表';

-- ----------------------------
-- Records of app_medical_card
-- ----------------------------
INSERT INTO `app_medical_card` VALUES ('1', '3', '0378263', '吕一明', '44088888888888888', '15555555555', '男', '2024-09-16 17:27:22');
INSERT INTO `app_medical_card` VALUES ('2', '3', '02204214', '张珊', '333000000000000000001', '1990000000000', '女', '2024-09-19 16:55:06');

-- ----------------------------
-- Table structure for app_medical_record
-- ----------------------------
DROP TABLE IF EXISTS `app_medical_record`;
CREATE TABLE `app_medical_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) NOT NULL COMMENT '就诊编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `doctor_id` bigint(20) NOT NULL COMMENT '医生ID',
  `booking_id` bigint(20) NOT NULL COMMENT '预约ID',
  `booking_sn` varchar(255) NOT NULL,
  `medical_card_id` bigint(20) NOT NULL COMMENT '就诊卡ID',
  `medical_card_sn` varchar(255) NOT NULL COMMENT '就诊卡号',
  `medical_card_name` varchar(255) NOT NULL COMMENT '就诊人名称',
  `diagnosis` text NOT NULL COMMENT '诊断结果',
  `treatment` text NOT NULL COMMENT '治疗方案',
  `record_time` datetime NOT NULL COMMENT '就诊日期',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='就诊记录表';

-- ----------------------------
-- Records of app_medical_record
-- ----------------------------
INSERT INTO `app_medical_record` VALUES ('2', '88287051366', '3', '2', '6', '0544347', '2', '02204214', '张珊', '头疼、咳嗽WWWW', '1、布洛芬一排\n2、xxxx颗粒2盒\n3、uuuuu胶囊1盒@@@', '2024-09-19 17:52:28', '2024-09-19 17:52:28');
INSERT INTO `app_medical_record` VALUES ('3', '88571474192', '3', '2', '6', '0544347', '2', '02204214', '张珊', '1111111111', '111111111111', '2024-09-19 18:37:58', '2024-09-19 18:37:58');

-- ----------------------------
-- Table structure for app_schedule
-- ----------------------------
DROP TABLE IF EXISTS `app_schedule`;
CREATE TABLE `app_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint(20) NOT NULL COMMENT '医生ID',
  `department_id` bigint(20) NOT NULL COMMENT '科室ID',
  `schedule_date` date NOT NULL COMMENT '排班日期',
  `moment` varchar(255) NOT NULL COMMENT '上午或下午',
  `quantity` int(10) NOT NULL COMMENT '就诊数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='医生排班表';

-- ----------------------------
-- Records of app_schedule
-- ----------------------------
INSERT INTO `app_schedule` VALUES ('1', '2', '2', '2024-12-22', '下午', '3', '2024-12-16 15:32:27');
INSERT INTO `app_schedule` VALUES ('2', '2', '2', '2024-12-23', '上午', '47', '2024-12-16 15:32:49');
INSERT INTO `app_schedule` VALUES ('3', '2', '2', '2024-12-23', '下午', '50', '2024-12-16 15:32:49');
INSERT INTO `app_schedule` VALUES ('4', '3', '2', '2024-12-23', '上午', '4', '2024-12-16 15:33:38');
INSERT INTO `app_schedule` VALUES ('5', '4', '3', '2024-12-22', '下午', '22', '2024-12-16 16:41:33');
INSERT INTO `app_schedule` VALUES ('6', '2', '2', '2024-12-24', '下午', '3', '2024-12-13 15:32:27');
INSERT INTO `app_schedule` VALUES ('8', '2', '2', '2024-12-28', '下午', '49', '2024-12-19 15:44:41');
INSERT INTO `app_schedule` VALUES ('9', '2', '2', '2024-12-25', '上午', '99', '2024-12-19 15:46:34');
INSERT INTO `app_schedule` VALUES ('10', '2', '2', '2024-12-22', '下午', '50', '2024-12-19 17:55:15');
INSERT INTO `app_schedule` VALUES ('11', '2', '2', '2024-12-23', '上午', '50', '2024-12-19 18:55:41');
INSERT INTO `app_schedule` VALUES ('12', '4', '3', '2024-12-21', '下午', '50', '2024-12-19 20:55:49');
INSERT INTO `app_schedule` VALUES ('13', '4', '3', '2024-12-21', '上午', '50', '2024-12-19 21:12:32');
INSERT INTO `app_schedule` VALUES ('14', '4', '3', '2024-12-22', '上午', '37', '2024-12-19 21:12:44');
INSERT INTO `app_schedule` VALUES ('15', '4', '3', '2024-12-25', '下午', '50', '2024-12-19 21:12:50');
INSERT INTO `app_schedule` VALUES ('16', '4', '3', '2025-12-26', '上午', '51', '2024-12-19 21:12:59');
INSERT INTO `app_schedule` VALUES ('17', '3', '2', '2024-12-28', '上午', '50', '2024-12-19 21:13:43');
INSERT INTO `app_schedule` VALUES ('18', '3', '2', '2024-12-28', '下午', '50', '2024-12-19 21:13:48');
INSERT INTO `app_schedule` VALUES ('19', '3', '2', '2024-12-26', '上午', '50', '2024-12-19 21:13:57');
INSERT INTO `app_schedule` VALUES ('20', '5', '4', '2024-12-30', '上午', '50', '2024-12-19 21:14:44');
INSERT INTO `app_schedule` VALUES ('21', '5', '4', '2024-12-27', '上午', '50', '2024-12-19 21:14:51');
INSERT INTO `app_schedule` VALUES ('22', '5', '4', '2024-12-27', '下午', '50', '2024-12-19 21:14:56');

-- ----------------------------
-- Table structure for app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `signature` varchar(255) DEFAULT NULL COMMENT '个性签名',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `app_user_username_uindex` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of app_user
-- ----------------------------
INSERT INTO `app_user` VALUES ('1', 'test', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', '18825000000', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.h0ndpUjL-jBetNPE_ACFyAHaHi?w=176&h=180&c=7&r=0&o=5&pid=1.7', '喵喵喵', '2024-07-21 21:25:53', '2024-06-04 22:36:23');
INSERT INTO `app_user` VALUES ('3', 'WRX', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', '18825000000', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.cn_mIqJN0Td_0Ono0xMEsQHaLL?w=124&h=188&c=7&r=0&o=5&pid=1.7', 'WRX', '2024-09-12 16:38:40', '2024-06-20 23:38:27');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL,
  `avatar` varchar(255) NOT NULL COMMENT '头像',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `role` varchar(255) NOT NULL COMMENT '角色(DOCTOR、ADMIN）',
  `doctor_id` bigint(20) DEFAULT NULL COMMENT '医生身份ID',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', 'admin', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', 'admi111111@qq.com', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.Cl3Qpg2UV6mqnRtrmQp14gHaHa?w=144&h=180&c=7&r=0&o=5&pid=1.7', '17288890984', 'ADMIN', null, '2024-09-19 22:32:25', '2024-06-18 11:01:01');
INSERT INTO `sys_admin` VALUES ('2', 'liming', '92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e', '2222@qq.com', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.Cl3Qpg2UV6mqnRtrmQp14gHaHa?w=144&h=180&c=7&r=0&o=5&pid=1.7', null, 'DOCTOR', '2', '2024-09-19 22:21:29', '2024-09-19 14:57:28');
INSERT INTO `sys_admin` VALUES ('3', 'wangli', '92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e', '111@qq.com', 'https://tse2-mm.cn.bing.net/th/id/OIP-C.JeYIxwivJLntBxC8YkV8gwHaE7?w=264&h=180&c=7&r=0&o=5&pid=1.7', null, 'DOCTOR', '3', '2024-09-19 21:13:33', '2024-09-19 20:54:43');
INSERT INTO `sys_admin` VALUES ('4', 'zhangqiang', '92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e', '222@qq.com', 'https://tse3-mm.cn.bing.net/th/id/OIP-C.bjNls8d77FpmCd4HnudldwHaEK?w=324&h=182&c=7&r=0&o=5&pid=1.7', null, 'DOCTOR', '4', '2024-09-19 20:55:37', '2024-09-19 20:55:05');
INSERT INTO `sys_admin` VALUES ('5', 'zhaohong', '92925488b28ab12584ac8fcaa8a27a0f497b2c62940c8f4fbc8ef19ebc87c43e', '2343@qq.com', 'https://tse4-mm.cn.bing.net/th/id/OIP-C.IJFOVWcEF0DNghFHLMtqPwHaHa?w=158&h=183&c=7&r=0&o=5&pid=1.7', null, 'DOCTOR', '5', '2024-09-19 21:14:37', '2024-09-19 21:14:27');
