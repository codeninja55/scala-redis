package com.redis

import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSpec, Matchers}
import org.scalatest.junit.JUnitRunner
import Matchers._

@RunWith(classOf[JUnitRunner])
class StreamsOperationsSpec extends FunSpec
                     with Matchers
                     with BeforeAndAfterEach
                     with BeforeAndAfterAll {

  val r = new RedisClient("localhost", 6379)

  override def beforeEach: Unit = {
  }

  override def afterEach: Unit = {
    r.flushdb
  }

  override def afterAll: Unit = {
    r.disconnect
  }

  describe("xlen") {
    it("returns the number of entries inside a stream") {
      r.xlen("mystream") should equal(Some(0))
    }
  }

  describe("xadd") {
    it("returns the string") {
      val optId =r.xadd("mystream", Map("field" -> "name"))
      optId shouldBe defined
      r.xlen("mystream") should equal(Some(1))
      optId.map(id => {
        r.xdel("mystream", id)
        r.xlen("mystream") should equal(Some(0))
      })
    }
  }

  describe("xgroup")  {
    it("create xgroup") {
      val aaa =r.xadd("mystream", Map("field" -> "name"))
      val optId =r.xgroup_create("mystream", "group")
    }
  }
}
