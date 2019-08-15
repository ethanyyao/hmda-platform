package hmda.dataBrowser.models

import slick.jdbc.GetResult

case class ModifiedLarEntity(
    filingYear: Int,
    lei: String,
    msaMd: Int,
    state: String,
    county: String,
    tract: String,
    conformingLoanLimit: String,
    loanProductType: String,
    dwellingCategory: String,
    ethnicityCategorization: String,
    raceCategorization: String,
    sexCategorization: String,
    actionTakenType: Int,
    purchaserType: Int,
    preapproval: Int,
    loanType: Int,
    loanPurpose: Int,
    lienStatus: Int,
    reverseMortgage: Int,
    lineOfCredits: Int,
    businessOrCommercial: Int,
    loanAmount: Double,
    loanValueRatio: String,
    interestRate: String,
    rateSpread: String,
    hoepaStatus: Int,
    totalLoanCosts: String,
    totalPoints: String,
    originationCharges: String,
    discountPoints: String,
    lenderCredits: String,
    loanTerm: String,
    paymentPenalty: String,
    rateSpreadIntro: String,
    amortization: Int,
    insertOnlyPayment: Int,
    baloonPayment: Int,
    otherAmortization: Int,
    propertyValue: String,
    constructionMethod: String,
    occupancyType: Int,
    homeSecurityPolicy: Int,
    landPropertyInterest: Int,
    totalUnits: String,
    mfAffordable: String,
    income: String,
    debtToIncome: String,
    creditScoreTypeApplicant: Int,
    creditScoreTypeCoApplicant: Int,
    ethnicityApplicant1: String,
    ethnicityApplicant2: String,
    ethnicityApplicant3: String,
    ethnicityApplicant4: String,
    ethnicityApplicant5: String,
    ethnicityCoApplicant1: String,
    ethnicityCoApplicant2: String,
    ethnicityCoApplicant3: String,
    ethnicityCoApplicant4: String,
    ethnicityCoApplicant5: String,
    ethnicityObservedApplicant: String,
    ethnicityObservedCoApplicant: String,
    raceApplicant1: String,
    raceApplicant2: String,
    raceApplicant3: String,
    raceApplicant4: String,
    raceApplicant5: String,
    rateCoApplicant1: String,
    rateCoApplicant2: String,
    rateCoApplicant3: String,
    rateCoApplicant4: String,
    rateCoApplicant5: String,
    raceObservedApplicant: Int,
    raceObservedCoApplicant: Int,
    sexApplicant: Int,
    sexCoApplicant: Int,
    observedSexApplicant: Int,
    observedSexCoApplicant: Int,
    ageApplicant: String,
    ageCoApplicant: String,
    applicantAgeGreaterThan62: String,
    coapplicantAgeGreaterThan62: String,
    applicationSubmission: Int,
    payable: Int,
    aus1: String,
    aus2: String,
    aus3: String,
    aus4: String,
    aus5: String,
    denialReason1: String,
    denialReason2: String,
    denialReason3: String,
    denialReason4: String,
    population: String,
    minorityPopulationPercent: String,
    ffiecMedFamIncome: String,
    medianIncomePercentage: String,
    ownerOccupiedUnits: String,
    oneToFourFamUnits: String,
    medianAge: Int
)

object ModifiedLarEntity {
  // See http://slick.lightbend.com/doc/3.2.0/sql.html?highlight=getresult#result-sets
  // we use shortcut << to get type inference instead of explicitly specifying nextInt or nextString based on the type
  implicit val getResult: GetResult[ModifiedLarEntity] =
    GetResult(
      r =>
        ModifiedLarEntity(
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<,
          r.<<
      ))

  def header =
    "activity_year,lei,derived_msa-md,state_code,county_code,census_tract,conforming_loan_limit,derived_loan_product_type,derived_dwelling_category,derived_ethnicity,derived_race,derived_sex,action_taken,purchaser_type,preapproval,loan_type,loan_purpose,lien_status,reverse_mortgage,open-end_line_of_credit,business_or_commercial_purpose,loan_amount,loan_to_value_ratio,interest_rate,rate_spread,hoepa_status,total_loan_costs,total_points_and_fees,origination_charges,discount_points,lender_credits,loan_term,prepayment_penalty_term,intro_rate_period,negative_amortization,interest_only_payment,balloon_payment,other_nonamortizing_features,property_value,construction_method,occupancy_type,manufactured_home_secured_property_type,manufactured_home_land_property_interest,total_units,multifamily_affordable_units,income,debt_to_income_ratio,applicant_credit_score_type,co-applicant_credit_score_type,applicant_ethnicity-1,applicant_ethnicity-2,applicant_ethnicity-3,applicant_ethnicity-4,applicant_ethnicity-5,co-applicant_ethnicity-1,co-applicant_ethnicity-2,co-applicant_ethnicity-3,co-applicant_ethnicity-4,co-applicant_ethnicity-5,applicant_ethnicity_observed,co-applicant_ethnicity_observed,applicant_race-1,applicant_race-2,applicant_race-3,applicant_race-4,applicant_race-5,co-applicant_race-1,co-applicant_race-2,co-applicant_race-3,co-applicant_race-4,co-applicant_race-5,applicant_race_observed,co-applicant_race_observed,applicant_sex,co-applicant_sex,applicant_sex_observed,co-applicant_sex_observed,applicant_age,co-applicant_age,applicant_age_above_62,co-applicant_age_above_62,submission_of_application,initially_payable_to_institution,aus-1,aus-2,aus-3,aus-4,aus-5,denial_reason-1,denial_reason-2,denial_reason-3,denial_reason-4,tract_population,tract_minority_population_percent,ffiec_msa_md_median_family_income,tract_to_msa_income_percentage,tract_owner_occupied_units,tract_one_to_four_family_homes,tract_median_age_of_housing_units"

  def headerPipe =
    "activity_year|lei|derived_msa-md|state_code|county_code|census_tract|conforming_loan_limit|derived_loan_product_type|derived_dwelling_category|derived_ethnicity|derived_race|derived_sex|action_taken|purchaser_type|preapproval|loan_type|loan_purpose|lien_status|reverse_mortgage|open-end_line_of_credit|business_or_commercial_purpose|loan_amount|loan_to_value_ratio|interest_rate|rate_spread|hoepa_status|total_loan_costs|total_points_and_fees|origination_charges|discount_points|lender_credits|loan_term|prepayment_penalty_term|intro_rate_period|negative_amortization|interest_only_payment|balloon_payment|other_nonamortizing_features|property_value|construction_method|occupancy_type|manufactured_home_secured_property_type|manufactured_home_land_property_interest|total_units|multifamily_affordable_units|income|debt_to_income_ratio|applicant_credit_score_type|co-applicant_credit_score_type|applicant_ethnicity-1|applicant_ethnicity-2|applicant_ethnicity-3|applicant_ethnicity-4|applicant_ethnicity-5|co-applicant_ethnicity-1|co-applicant_ethnicity-2|co-applicant_ethnicity-3|co-applicant_ethnicity-4|co-applicant_ethnicity-5|applicant_ethnicity_observed|co-applicant_ethnicity_observed|applicant_race-1|applicant_race-2|applicant_race-3|applicant_race-4|applicant_race-5|co-applicant_race-1|co-applicant_race-2|co-applicant_race-3|co-applicant_race-4|co-applicant_race-5|applicant_race_observed|co-applicant_race_observed|applicant_sex|co-applicant_sex|applicant_sex_observed|co-applicant_sex_observed|applicant_age|co-applicant_age|applicant_age_above_62|co-applicant_age_above_62|submission_of_application|initially_payable_to_institution|aus-1|aus-2|aus-3|aus-4|aus-5|denial_reason-1|denial_reason-2|denial_reason-3|denial_reason-4|tract_population|tract_minority_population_percent|ffiec_msa_md_median_family_income|tract_to_msa_income_percentage|tract_owner_occupied_units|tract_one_to_four_family_homes|tract_median_age_of_housing_units"

  implicit class CsvOps(modifiedLarEntity: ModifiedLarEntity) {
    import modifiedLarEntity._
    def toCsv =
      s"""$filingYear,$lei,$msaMd,$state,$county,$tract,$conformingLoanLimit,$loanProductType,$dwellingCategory,$ethnicityCategorization,$raceCategorization,$sexCategorization,$actionTakenType,$purchaserType,$preapproval,$loanType,$loanPurpose,$lienStatus,$reverseMortgage,$lineOfCredits,$businessOrCommercial,$loanAmount,$loanValueRatio,$interestRate,$rateSpread,$hoepaStatus,$totalLoanCosts,$totalPoints,$originationCharges,$discountPoints,$lenderCredits,$loanTerm,$paymentPenalty,$rateSpreadIntro,$amortization,$insertOnlyPayment,$baloonPayment,$otherAmortization,$propertyValue,$constructionMethod,$occupancyType,$homeSecurityPolicy,$landPropertyInterest,$totalUnits,$mfAffordable,$income,$debtToIncome,$creditScoreTypeApplicant,$creditScoreTypeCoApplicant,$ethnicityApplicant1,$ethnicityApplicant2,$ethnicityApplicant3,$ethnicityApplicant4,$ethnicityApplicant5,$ethnicityCoApplicant1,$ethnicityCoApplicant2,$ethnicityCoApplicant3,$ethnicityCoApplicant4,$ethnicityCoApplicant5,$ethnicityObservedApplicant,$ethnicityObservedCoApplicant,$raceApplicant1,$raceApplicant2,$raceApplicant3,$raceApplicant4,$raceApplicant5,$rateCoApplicant1,$rateCoApplicant2,$rateCoApplicant3,$rateCoApplicant4,$rateCoApplicant5,$raceObservedApplicant,$raceObservedCoApplicant,$sexApplicant,$sexCoApplicant,$observedSexApplicant,$observedSexCoApplicant,$ageApplicant,$ageCoApplicant,$applicantAgeGreaterThan62,$coapplicantAgeGreaterThan62,$applicationSubmission,$payable,$aus1,$aus2,$aus3,$aus4,$aus5,$denialReason1,$denialReason2,$denialReason3,$denialReason4,$population,$minorityPopulationPercent,$ffiecMedFamIncome,$medianIncomePercentage,$ownerOccupiedUnits,$oneToFourFamUnits,$medianAge"""
  }

  implicit class PipeOps(modifiedLarEntity: ModifiedLarEntity) {
    import modifiedLarEntity._
    def toPipe =
      s"$filingYear|$lei|$msaMd|$state|$county|$tract|$conformingLoanLimit|$loanProductType|$dwellingCategory|$ethnicityCategorization|$raceCategorization|$sexCategorization|$actionTakenType|$purchaserType|$preapproval|$loanType|$loanPurpose|$lienStatus|$reverseMortgage|$lineOfCredits|$businessOrCommercial|$loanAmount|$loanValueRatio|$interestRate|$rateSpread|$hoepaStatus|$totalLoanCosts|$totalPoints|$originationCharges|$discountPoints|$lenderCredits|$loanTerm|$paymentPenalty|$rateSpreadIntro|$amortization|$insertOnlyPayment|$baloonPayment|$otherAmortization|$propertyValue|$constructionMethod|$occupancyType|$homeSecurityPolicy|$landPropertyInterest|$totalUnits|$mfAffordable|$income|$debtToIncome|$creditScoreTypeApplicant|$creditScoreTypeCoApplicant|$ethnicityApplicant1|$ethnicityApplicant2|$ethnicityApplicant3|$ethnicityApplicant4|$ethnicityApplicant5|$ethnicityCoApplicant1|$ethnicityCoApplicant2|$ethnicityCoApplicant3|$ethnicityCoApplicant4|$ethnicityCoApplicant5|$ethnicityObservedApplicant|$ethnicityObservedCoApplicant|$raceApplicant1|$raceApplicant2|$raceApplicant3|$raceApplicant4|$raceApplicant5|$rateCoApplicant1|$rateCoApplicant2|$rateCoApplicant3|$rateCoApplicant4|$rateCoApplicant5|$raceObservedApplicant|$raceObservedCoApplicant|$sexApplicant|$sexCoApplicant|$observedSexApplicant|$observedSexCoApplicant|$ageApplicant|$ageCoApplicant|$applicantAgeGreaterThan62|$coapplicantAgeGreaterThan62|$applicationSubmission|$payable|$aus1|$aus2|$aus3|$aus4|$aus5|$denialReason1|$denialReason2|$denialReason3|$denialReason4|$population|$minorityPopulationPercent|$ffiecMedFamIncome|$medianIncomePercentage|$ownerOccupiedUnits|$oneToFourFamUnits|$medianAge"
  }
}
