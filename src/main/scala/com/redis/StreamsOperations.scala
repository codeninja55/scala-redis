package com.redis
import serialization._


trait StreamsOperations {self: Redis =>

  // XLEN(https://redis.io/commands/XLEN)
  //
  def xlen(name: String)(implicit format: Format): Option[Long] =
    send("XLEN", List(name))(asLong)

  def xadd(name: String, fields: Map[String, String], id:String="*")(implicit format: Format): Option[String] = {
    send("XADD", name :: id :: flattenPairs(fields))(asBulk[String])
  }

  def xdel(name: String, field: Any, fields: Any*)(implicit format: Format): Option[Long] = {
    send("XDEL", List(name, field))(asLong)
  }

  def xrange[K,V](name: String, min:String="-", max:String="+")(implicit format: Format, parseK: Parse[K], parseV: Parse[V]): Option[Map[K, V]]= {
    send("XRANGE", List(name , min , max))(asListPairs[K, V].map(_.flatten.toMap))
  }
}
