package com.liorregev.spark.blockchain.ethereum.block

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources.{BaseRelation, RelationProvider}

class DefaultSource extends RelationProvider  {
  override def createRelation(sqlContext: SQLContext, parameters: Map[String, String]): BaseRelation = {
    val path =
      parameters.getOrElse("path", sys.error("'path' must be specified with files containing Ethereum blockchain data."))
    val enrich = parameters.getOrElse("enrich", "false").toBoolean
    EthereumBlockRelation(path, enrich)(sqlContext)
  }
}
