DROP TABLE IF EXISTS `data_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_log` (
  `log_id` bigint(20) NOT NULL,
  `data_id` bigint(20) NOT NULL,
  KEY `FKltf02ma87wdx0rtdc5tewl7ok` (`data_id`),
  KEY `FK45ly7unjgoi1sy6jcarmxi69x` (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_log`
--

LOCK TABLES `data_log` WRITE;
/*!40000 ALTER TABLE `data_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `data_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ethereum_log`
--

DROP TABLE IF EXISTS `ethereum_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ethereum_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tx_hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ethereum_log`
--

LOCK TABLES `ethereum_log` WRITE;
/*!40000 ALTER TABLE `ethereum_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `ethereum_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ethereum_log_data`
--

DROP TABLE IF EXISTS `ethereum_log_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ethereum_log_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hex_data` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ag5nbmmcshke3qar3s5pa5l1v` (`hex_data`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ethereum_log_data`
--

LOCK TABLES `ethereum_log_data` WRITE;
/*!40000 ALTER TABLE `ethereum_log_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `ethereum_log_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_log`
--

DROP TABLE IF EXISTS `topic_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_log` (
  `log_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  KEY `FK7qnw678bxsdbk2hbt5ulgj50q` (`topic_id`),
  KEY `FKm0tl2k7bt39oad23ixyq49nso` (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_log`
--

LOCK TABLES `topic_log` WRITE;
/*!40000 ALTER TABLE `topic_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic_word`
--

DROP TABLE IF EXISTS `topic_word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic_word` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hash_str` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nra823doqjo8ohdph6n581b5j` (`hash_str`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic_word`
--

LOCK TABLES `topic_word` WRITE;
/*!40000 ALTER TABLE `topic_word` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic_word` ENABLE KEYS */;
UNLOCK TABLES;
