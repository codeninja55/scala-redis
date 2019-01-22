package com.redis
import serialization._


trait StreamsOperations {self: Redis =>

  // XLEN(https://redis.io/commands/XLEN)
  //
  def xlen(name: String)(implicit format: Format): Option[Long] =
    send("XLEN", name)(asLong)

}
