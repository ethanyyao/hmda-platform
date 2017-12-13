package hmda.publication.reports.disclosure

import akka.NotUsed
import akka.stream.scaladsl.Source
import hmda.model.fi.lar.LoanApplicationRegister
import hmda.model.publication.reports.ApplicantIncomeEnum._
import hmda.model.publication.reports.EthnicityEnum._
import hmda.model.publication.reports.GenderEnum._
import hmda.model.publication.reports.MinorityStatusEnum._
import hmda.model.publication.reports.RaceEnum._
import hmda.publication.reports._
import hmda.publication.reports.util.EthnicityUtil.filterEthnicity
import hmda.publication.reports.util.GenderUtil.filterGender
import hmda.publication.reports.util.MinorityStatusUtil.filterMinorityStatus
import hmda.publication.reports.util.RaceUtil.filterRace
import hmda.publication.reports.util.ReportUtil._
import hmda.publication.reports.util.ReportsMetaDataLookup
import spray.json.JsValue

import scala.concurrent.Future

trait D11X
object D11X {


  def generate[ec: EC, mat: MAT, as: AS](
                                          report: D8X,
                                          larSource: Source[LoanApplicationRegister, NotUsed],
                                          fipsCode: Int,
                                          respondentId: String,
                                          institutionNameF: Future[String]
                                        ): Future[JsValue] = {

    val metaData = ReportsMetaDataLookup.values(report.reportId)

    val lars = larSource
      .filter(lar => lar.respondentId == respondentId)
      .filter(lar => lar.geography.msa != "NA")
      .filter(lar => lar.geography.msa.toInt == fipsCode)
      .filter(report.filters)

    val incomeIntervals = larsByIncomeInterval(
      lars.filter(lar => lar.applicant.income != "NA"),
      calculateMedianIncomeIntervals(fipsCode)
    )
    val msa = msaReport(fipsCode.toString).toJsonFormat
    val reportDate = formattedCurrentDate
    val yearF = calculateYear(lars)

    for {
      institutionName <- institutionNameF
      year <- yearF

      e1 <- pricingData(filterEthnicity(lars, HispanicOrLatino))
      e2 <- pricingData(filterEthnicity(lars, NotHispanicOrLatino))
      e3 <- pricingData(filterEthnicity(lars, JointEthnicity))
      e4 <- pricingData(filterEthnicity(lars, NotAvailable))

      r1 <- pricingData(filterRace(lars, AmericanIndianOrAlaskaNative))
      r2 <- pricingData(filterRace(lars, Asian))
      r3 <- pricingData(filterRace(lars, BlackOrAfricanAmerican))
      r4 <- pricingData(filterRace(lars, HawaiianOrPacific))
      r5 <- pricingData(filterRace(lars, White))
      r6 <- pricingData(filterRace(lars, TwoOrMoreMinority))
      r7 <- pricingData(filterRace(lars, JointRace))
      r8 <- pricingData(filterRace(lars, NotProvided))

      m1 <- pricingData(filterMinorityStatus(lars, WhiteNonHispanic))
      m2 <- pricingData(filterMinorityStatus(lars, OtherIncludingHispanic))

      g1 <- pricingData(filterGender(lars, Male))
      g2 <- pricingData(filterGender(lars, Female))
      g3 <- pricingData(filterGender(lars, JointGender))
      g4 <- pricingData(filterGender(lars, GenderNotAvailable))

      i1 <- pricingData(incomeIntervals(LessThan50PercentOfMSAMedian))
      i2 <- pricingData(incomeIntervals(Between50And79PercentOfMSAMedian))
      i3 <- pricingData(incomeIntervals(Between80And99PercentOfMSAMedian))
      i4 <- pricingData(incomeIntervals(Between100And119PercentOfMSAMedian))
      i5 <- pricingData(incomeIntervals(GreaterThan120PercentOfMSAMedian))
      i6 <- pricingData(lars.filter(lar => lar.applicant.income == "NA"))

      tractMinorityComposition1 <- ""
      tractMinorityComposition2 <- ""
      tractMinorityComposition3 <- ""
      tractMinorityComposition4 <- ""
      tractMinorityComposition5 <- ""

      tractIncome1 <- ""
      tractIncome2 <- ""
      tractIncome3 <- ""
      tractIncome4 <- ""

    } yield {
      s"""
       |    "respondentId": "$respondentId",
       |    "institutionName": "$institutionName",
       |    "table": "${metaData.reportTable} ",
       |    "type": "Disclosure",
       |    "description": "${metaData.description}",
       |    "year": "$year",
       |    "reportDate": "$reportDate",
       |    "msa": $msa,
       |    "borrowerCharacteristics": [
       |        {
       |            "characteristic": "Race",
       |            "races": [
       |                {
       |                    "race": "American Indian/Alaska Native",
       |                    "pricingInformation": $r1
       |                },
       |                {
       |                    "race": "Asian",
       |                    "pricingInformation": $r2
       |                },
       |                {
       |                    "race": "Black or African American",
       |                    "pricingInformation": $r3
       |                },
       |                {
       |                    "race": "Native Hawaiian or Other Pacific Islander",
       |                    "pricingInformation": $r4
       |                },
       |                {
       |                    "race": "White",
       |                    "pricingInformation": $r5
       |                },
       |                {
       |                    "race": "2 or more minority races",
       |                    "pricingInformation": $r6
       |                },
       |                {
       |                    "race": "Joint (White/Minority Race)",
       |                    "pricingInformation": $r7
       |                },
       |                {
       |                    "race": "Race Not Available",
       |                    "pricingInformation": $r8
       |                }
       |            ]
       |        },
       |        {
       |            "characteristic": "Ethnicity",
       |            "ethnicities": [
       |                {
       |                    "ethnicity": "Hispanic or Latino",
       |                    "pricingInformation": $e1
       |                },
       |                {
       |                    "ethnicity": "Not Hispanic or Latino",
       |                    "pricingInformation": $e2
       |                },
       |                {
       |                    "ethnicity": "Joint (Hispanic or Latino/Not Hispanic or Latino)",
       |                    "pricingInformation": $e3
       |                },
       |                {
       |                    "ethnicity": "Ethnicity Not Available",
       |                    "pricingInformation": $e4
       |                }
       |            ]
       |        },
       |        {
       |            "characteristic": "Minority Status",
       |            "minorityStatuses": [
       |                {
       |                    "minorityStatus": "White Non-Hispanic",
       |                    "pricingInformation": $m1
       |                },
       |                {
       |                    "minorityStatus": "Others, Including Hispanic",
       |                    "pricingInformation": $m2
       |                }
       |            ]
       |        },
       |        {
       |            "characteristic": "Income",
       |            "incomes": [
       |                {
       |                    "income": "Less than 50% of MSA/MD median",
       |                    "pricingInformation": $i1
       |                },
       |                {
       |                    "income": "50-79% of MSA/MD median",
       |                    "pricingInformation": $i2
       |                },
       |                {
       |                    "income": "80-99% of MSA/MD median",
       |                    "pricingInformation": $i3
       |                },
       |                {
       |                    "income": "100-119% of MSA/MD median",
       |                    "pricingInformation": $i4
       |                },
       |                {
       |                    "income": "120% or more of MSA/MD median",
       |                    "pricingInformation": $i5
       |                },
       |                {
       |                    "income": "Income Not Available",
       |                    "pricingInformation": $i6
       |                }
       |            ]
       |        },
       |        {
       |            "characteristic": "Gender",
       |            "genders": [
       |                {
       |                    "gender": "Male",
       |                    "pricingInformation": $g1
       |                },
       |                {
       |                    "gender": "Female",
       |                    "pricingInformation": $g2
       |                },
       |                {
       |                    "gender": "Joint (Male/Female)",
       |                    "pricingInformation": $g3
       |                },
       |                {
       |                    "gender": "Gender Not Available",
       |                    "pricingInformation": $g4
       |                }
       |            ]
       |        }
       |    ],
       |    "censusTractCharacteristics": [
       |        {
       |            "characteristic": "Racial/Ethnic Composition",
       |            "compositions": [
       |                {
       |                    "composition": "Less than 10% minority",
       |                    "pricingInformation": $tractMinorityComposition1
       |                },
       |                {
       |                    "composition": "10-19% minority",
       |                    "pricingInformation": $tractMinorityComposition2
       |                },
       |                {
       |                    "composition": "20-49% minority",
       |                    "pricingInformation": $tractMinorityComposition3
       |                },
       |                {
       |                    "composition": "50-79% minority",
       |                    "pricingInformation": $tractMinorityComposition4
       |                },
       |                {
       |                    "composition": "80-100% minority",
       |                    "pricingInformation": $tractMinorityComposition5
       |                }
       |            ]
       |        },
       |        {
       |            "characteristic": "Income Characteristics",
       |            "incomes": [
       |                {
       |                    "income": "Low income",
       |                    "pricingInformation": $tractIncome1
       |                },
       |                {
       |                    "income": "Moderate income",
       |                    "pricingInformation": $tractIncome2
       |                },
       |                {
       |                    "income": "Middle income",
       |                    "pricingInformation": $tractIncome3
       |                },
       |                {
       |                    "income": "Upper income",
       |                    "pricingInformation": $tractIncome4
       |                }
       |            ]
       |        }
       |    ]
       |}
     """.stripMargin
    }
  }

  def pricingData(larSource: Source[LoanApplicationRegister, NotUsed]): Future[String] = {
    s"""
       |[
       |    {
       |        "pricing": "No Reported Pricing Data",
       |        "count": 0,
       |        "value": 0
       |    },
       |    {
       |        "pricing": "Reported Pricing Data",
       |        "count": 0,
       |        "value": 0
       |    },
       |    {
       |        "pricing": "1.50 - 1.99",
       |        "count": 0,
       |        "value": 0
       |    },
       |    {
       |        "pricing": "2.00 - 2.49",
       |        "count": 0,
       |        "value": 0
       |    },
       |    {
       |        "pricing": "2.50 - 2.99",
       |        "count": 0,
       |        "value": 0
       |    },
       |    {
       |        "pricing": "3.00 - 3.99",
       |        "count": 0,
       |        "value": 0
       |    },
       |    {
       |        "pricing": "4.00 - 4.99",
       |        "count": 0,
       |        "value": 0
       |    },
       |    {
       |        "pricing": "5 or more",
       |        "count": 0,
       |        "value": 0
       |    },
       |    {
       |        "pricing": "Mean",
       |        "count": 0,
       |        "value": "None"
       |    },
       |    {
       |        "pricing": "Median",
       |        "count": 0,
       |        "value": "None"
       |    }
       |]
     """.stripMargin
  }
}
