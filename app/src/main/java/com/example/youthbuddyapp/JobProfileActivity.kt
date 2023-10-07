package com.example.youthbuddyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.youthbuddyapp.databinding.ActivityJobProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class JobProfileActivity : AppCompatActivity() {

    private lateinit var jobProfileBinding: ActivityJobProfileBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jobProfileBinding = ActivityJobProfileBinding.inflate(layoutInflater)
        setContentView(jobProfileBinding.root)

        setUpOnClickListeners()
        setUpQuestions()
        allowSingleCheckbox()

        jobProfileBinding.saveButton.setOnClickListener {
            if (checkForUncheckedQuestions()) {
                Toast.makeText(
                    applicationContext,
                    "You have unanswered questions",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                var jobDomain = ""
                if (jobProfileBinding.employmentDomain.answer1.isChecked)
                    jobDomain = jobProfileBinding.employmentDomain.answer1.text.toString()
                else if (jobProfileBinding.employmentDomain.answer2.isChecked)
                    jobDomain = jobProfileBinding.employmentDomain.answer2.text.toString()
                else if (jobProfileBinding.employmentDomain.answer3.isChecked)
                    jobDomain = jobProfileBinding.employmentDomain.answer3.text.toString()
                else if (jobProfileBinding.employmentDomain.answer4.isChecked)
                    jobDomain = jobProfileBinding.employmentDomain.answer4.text.toString()
                else if (jobProfileBinding.employmentDomain.answer5.isChecked)
                    jobDomain = jobProfileBinding.employmentDomain.answer5.text.toString()
                else if (jobProfileBinding.employmentDomain.answer6.isChecked)
                    jobDomain =
                        jobProfileBinding.employmentDomain.otherEmploymentDomain.editText?.text.toString()

                var skills = ""
                if (jobProfileBinding.skills.answer1.isChecked)
                    skills = jobProfileBinding.skills.answer1.text.toString()
                else if (jobProfileBinding.skills.answer2.isChecked)
                    skills = jobProfileBinding.skills.answer2.text.toString()
                else if (jobProfileBinding.skills.answer3.isChecked)
                    skills = jobProfileBinding.skills.answer3.text.toString()
                else if (jobProfileBinding.skills.answer4.isChecked)
                    skills = jobProfileBinding.skills.answer4.text.toString()
                else if (jobProfileBinding.skills.answer5.isChecked)
                    skills = jobProfileBinding.skills.answer5.text.toString()
                else if (jobProfileBinding.skills.answer6.isChecked)
                    skills =
                        jobProfileBinding.skills.otherEmploymentDomain.editText?.text.toString()

                val userData = hashMapOf(
                    "location" to jobProfileBinding.locationTextView.editText?.text.toString(),
                    "employmentDomain" to jobDomain,
                    "skillsDevelop" to skills
                )
                db.collection("users").document(auth.currentUser?.uid.toString())
                    .update(userData as Map<String, Any>)
                    .addOnSuccessListener {
                        val intent = Intent(this, MainMenuActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener { e -> Log.d("uploadCheck", e.toString()) }
            }
        }

    }

    private fun setUpOnClickListeners() {
        jobProfileBinding.employmentStatus.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.employmentStatus.twoAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.employmentStatus.twoAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.employmentStatus.twoAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.stableInternet.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.stableInternet.twoAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.stableInternet.twoAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.stableInternet.twoAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.familyIncome.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.familyIncome.fourAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.familyIncome.fourAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.familyIncome.fourAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.employmentDomain.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.employmentDomain.sixAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.employmentDomain.sixAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.employmentDomain.sixAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.skills.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.skills.sixAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.skills.sixAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.skills.sixAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.familyIncome.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.familyIncome.fourAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.familyIncome.fourAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.familyIncome.fourAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.educationLevel.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.educationLevel.fourAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.educationLevel.fourAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.educationLevel.fourAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.coursesPlan.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.coursesPlan.twoAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.coursesPlan.twoAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.coursesPlan.twoAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.coursesField.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.coursesField.sixAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.coursesField.sixAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.coursesField.sixAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.jobType.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.jobType.fourAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.jobType.fourAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.jobType.fourAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.workingEnvironment.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.workingEnvironment.sixAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.workingEnvironment.sixAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.workingEnvironment.sixAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.workExperience.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.workExperience.twoAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.workExperience.twoAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.workExperience.twoAnswersCheckbox.visibility = View.VISIBLE
        }

        jobProfileBinding.jobExpectations.expandArrowImageView.setOnClickListener {
            if (jobProfileBinding.jobExpectations.sixAnswersCheckbox.visibility == View.VISIBLE)
                jobProfileBinding.jobExpectations.sixAnswersCheckbox.visibility = View.GONE
            else
                jobProfileBinding.jobExpectations.sixAnswersCheckbox.visibility = View.VISIBLE
        }
    }

    private fun setUpQuestions() {
        jobProfileBinding.employmentStatus.questionTitle.text = "Are you currently employed?"
        jobProfileBinding.employmentStatus.answer1.text = "Yes"
        jobProfileBinding.employmentStatus.answer2.text = "No"

        jobProfileBinding.stableInternet.questionTitle.text =
            "Do you have stable internet access at home?"
        jobProfileBinding.stableInternet.answer1.text = "Yes"
        jobProfileBinding.stableInternet.answer2.text = "No"

        jobProfileBinding.familyIncome.questionTitle.text =
            "What is your family's monthly income range?"
        jobProfileBinding.familyIncome.answer1.text = "Below $500"
        jobProfileBinding.familyIncome.answer2.text = "$500 - $1000"
        jobProfileBinding.familyIncome.answer3.text = "$1000 - $2000"
        jobProfileBinding.familyIncome.answer4.text = "Above $2000"

        jobProfileBinding.employmentDomain.questionTitle.text = "Are you currently employed?"
        jobProfileBinding.employmentDomain.answer1.text = "Technology & Computing"
        jobProfileBinding.employmentDomain.answer2.text = "Health & Medicine"
        jobProfileBinding.employmentDomain.answer3.text = "Social & Human Sciences"
        jobProfileBinding.employmentDomain.answer4.text = "Engineering & Construction"
        jobProfileBinding.employmentDomain.answer5.text = "Education & Training"
        jobProfileBinding.employmentDomain.answer6.text = "Other:"

        jobProfileBinding.skills.questionTitle.text =
            "Select the skills you posses or wish to develop:"
        jobProfileBinding.skills.answer1.text = "Programming & Software Development"
        jobProfileBinding.skills.answer2.text = "Communication & Marketing"
        jobProfileBinding.skills.answer3.text = "Management & Leadership"
        jobProfileBinding.skills.answer4.text = "Medical & Healthcare Skills"
        jobProfileBinding.skills.answer5.text = "Artistic & Creative Skills"
        jobProfileBinding.skills.answer6.text = "Other:"

        jobProfileBinding.educationLevel.questionTitle.text = "Current level of education:"
        jobProfileBinding.educationLevel.answer1.text = "No high school diploma"
        jobProfileBinding.educationLevel.answer2.text = "High school diploma"
        jobProfileBinding.educationLevel.answer3.text = "Post high school studies"
        jobProfileBinding.educationLevel.answer4.text = "Higher education"

        jobProfileBinding.coursesPlan.questionTitle.text =
            "Are you planning to take courses in the future?"
        jobProfileBinding.coursesPlan.answer1.text = "Yes"
        jobProfileBinding.coursesPlan.answer2.text = "No"

        jobProfileBinding.coursesField.questionTitle.text = "If yes, in which field?"
        jobProfileBinding.coursesField.answer1.text = "Technology & Computing"
        jobProfileBinding.coursesField.answer2.text = "Health & Medicine"
        jobProfileBinding.coursesField.answer3.text = "Social & Human Sciences"
        jobProfileBinding.coursesField.answer4.text = "Engineering & Construction"
        jobProfileBinding.coursesField.answer5.text = "Education & Training"
        jobProfileBinding.coursesField.answer6.text = "Other:"

        jobProfileBinding.jobType.questionTitle.text = "Select the type of job you are looking for:"
        jobProfileBinding.jobType.answer1.text = "Full-time"
        jobProfileBinding.jobType.answer2.text = "Part time"
        jobProfileBinding.jobType.answer3.text = "Freelance"
        jobProfileBinding.jobType.answer4.text = "Internship"

        jobProfileBinding.workingEnvironment.questionTitle.text =
            "Select your preferred working environment:"
        jobProfileBinding.workingEnvironment.answer1.text = "Corporation"
        jobProfileBinding.workingEnvironment.answer2.text = "Startup"
        jobProfileBinding.workingEnvironment.answer3.text = "Remote"
        jobProfileBinding.workingEnvironment.answer4.text = "Non-profit"
        jobProfileBinding.workingEnvironment.answer5.text = "Government"
        jobProfileBinding.workingEnvironment.answer6.text = "Other:"

        jobProfileBinding.workExperience.questionTitle.text =
            "Do you have experience in the chosen field?"
        jobProfileBinding.workExperience.answer1.text = "Yes"
        jobProfileBinding.workExperience.answer2.text = "No"

        jobProfileBinding.jobExpectations.questionTitle.text =
            "What are your expectations from a job?"
        jobProfileBinding.jobExpectations.answer1.text = "Stable income"
        jobProfileBinding.jobExpectations.answer2.text = "Career advancement opportunities"
        jobProfileBinding.jobExpectations.answer3.text = "Flexible working hours"
        jobProfileBinding.jobExpectations.answer4.text = "Supportive working environment"
        jobProfileBinding.jobExpectations.answer5.text = "Learning and development opportunities"
        jobProfileBinding.jobExpectations.answer6.text = "Other:"

    }

    private fun checkForUncheckedQuestions(): Boolean {
        var missedAQuestion = false
        if (!jobProfileBinding.employmentStatus.answer1.isChecked && !jobProfileBinding.employmentStatus.answer2.isChecked)
            missedAQuestion = true
        if (!jobProfileBinding.stableInternet.answer1.isChecked && !jobProfileBinding.stableInternet.answer2.isChecked)
            missedAQuestion = true
        if (!jobProfileBinding.familyIncome.answer1.isChecked && !jobProfileBinding.familyIncome.answer2.isChecked && !jobProfileBinding.familyIncome.answer3.isChecked && !jobProfileBinding.familyIncome.answer4.isChecked)
            missedAQuestion = true
        if (jobProfileBinding.employmentStatus.answer1.isChecked)
            if (!jobProfileBinding.employmentDomain.answer1.isChecked && !jobProfileBinding.employmentDomain.answer2.isChecked && !jobProfileBinding.employmentDomain.answer3.isChecked && !jobProfileBinding.employmentDomain.answer4.isChecked && !jobProfileBinding.employmentDomain.answer5.isChecked && !jobProfileBinding.employmentDomain.answer6.isChecked)
                missedAQuestion = true
        if (!jobProfileBinding.skills.answer1.isChecked && !jobProfileBinding.skills.answer2.isChecked && !jobProfileBinding.skills.answer3.isChecked && !jobProfileBinding.skills.answer4.isChecked && !jobProfileBinding.skills.answer5.isChecked && !jobProfileBinding.skills.answer6.isChecked)
            missedAQuestion = true
        if (!jobProfileBinding.educationLevel.answer1.isChecked && !jobProfileBinding.educationLevel.answer2.isChecked && !jobProfileBinding.educationLevel.answer3.isChecked && !jobProfileBinding.educationLevel.answer4.isChecked)
            missedAQuestion = true
        if (!jobProfileBinding.coursesPlan.answer1.isChecked && !jobProfileBinding.coursesPlan.answer2.isChecked)
            missedAQuestion = true
        if (jobProfileBinding.coursesPlan.answer1.isChecked)
            if (!jobProfileBinding.educationLevel.answer1.isChecked && !jobProfileBinding.educationLevel.answer2.isChecked && !jobProfileBinding.educationLevel.answer3.isChecked && !jobProfileBinding.educationLevel.answer4.isChecked)
                missedAQuestion = true
        if (!jobProfileBinding.jobType.answer1.isChecked && !jobProfileBinding.jobType.answer2.isChecked && !jobProfileBinding.jobType.answer3.isChecked && !jobProfileBinding.jobType.answer4.isChecked)
            missedAQuestion = true
        if (!jobProfileBinding.workingEnvironment.answer1.isChecked && !jobProfileBinding.workingEnvironment.answer2.isChecked && !jobProfileBinding.workingEnvironment.answer3.isChecked && !jobProfileBinding.workingEnvironment.answer4.isChecked && !jobProfileBinding.workingEnvironment.answer5.isChecked && !jobProfileBinding.workingEnvironment.answer6.isChecked)
            missedAQuestion = true
        if (!jobProfileBinding.workExperience.answer1.isChecked && !jobProfileBinding.workExperience.answer2.isChecked)
            missedAQuestion = true
        if (!jobProfileBinding.jobExpectations.answer1.isChecked && !jobProfileBinding.jobExpectations.answer2.isChecked && !jobProfileBinding.jobExpectations.answer3.isChecked && !jobProfileBinding.jobExpectations.answer4.isChecked && !jobProfileBinding.jobExpectations.answer5.isChecked && !jobProfileBinding.jobExpectations.answer6.isChecked)
            missedAQuestion = true
        return missedAQuestion
    }

    private fun allowSingleCheckbox() {
        jobProfileBinding.employmentStatus.answer1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                jobProfileBinding.employmentStatus.answer2.isChecked = false
        }

        jobProfileBinding.employmentStatus.answer2.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                jobProfileBinding.employmentStatus.answer1.isChecked = false
        }

        jobProfileBinding.stableInternet.answer1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                jobProfileBinding.stableInternet.answer2.isChecked = false
        }

        jobProfileBinding.stableInternet.answer2.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                jobProfileBinding.stableInternet.answer1.isChecked = false
        }

        jobProfileBinding.familyIncome.answer1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                jobProfileBinding.familyIncome.answer2.isChecked = false
                jobProfileBinding.familyIncome.answer3.isChecked = false
                jobProfileBinding.familyIncome.answer4.isChecked = false
            }
        }

        jobProfileBinding.familyIncome.answer2.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                jobProfileBinding.familyIncome.answer1.isChecked = false
                jobProfileBinding.familyIncome.answer3.isChecked = false
                jobProfileBinding.familyIncome.answer4.isChecked = false
            }
        }

        jobProfileBinding.familyIncome.answer3.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                jobProfileBinding.familyIncome.answer1.isChecked = false
                jobProfileBinding.familyIncome.answer2.isChecked = false
                jobProfileBinding.familyIncome.answer4.isChecked = false
            }
        }

        jobProfileBinding.familyIncome.answer4.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                jobProfileBinding.familyIncome.answer1.isChecked = false
                jobProfileBinding.familyIncome.answer2.isChecked = false
                jobProfileBinding.familyIncome.answer3.isChecked = false
            }
        }

        jobProfileBinding.educationLevel.answer1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                jobProfileBinding.educationLevel.answer2.isChecked = false
                jobProfileBinding.educationLevel.answer3.isChecked = false
                jobProfileBinding.educationLevel.answer4.isChecked = false
            }
        }

        jobProfileBinding.educationLevel.answer2.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                jobProfileBinding.educationLevel.answer1.isChecked = false
                jobProfileBinding.educationLevel.answer3.isChecked = false
                jobProfileBinding.educationLevel.answer4.isChecked = false
            }
        }

        jobProfileBinding.educationLevel.answer3.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                jobProfileBinding.educationLevel.answer1.isChecked = false
                jobProfileBinding.educationLevel.answer2.isChecked = false
                jobProfileBinding.educationLevel.answer4.isChecked = false
            }
        }

        jobProfileBinding.educationLevel.answer4.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                jobProfileBinding.educationLevel.answer1.isChecked = false
                jobProfileBinding.educationLevel.answer2.isChecked = false
                jobProfileBinding.educationLevel.answer3.isChecked = false
            }
        }

        jobProfileBinding.coursesPlan.answer1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                jobProfileBinding.coursesPlan.answer2.isChecked = false
        }

        jobProfileBinding.coursesPlan.answer2.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                jobProfileBinding.coursesPlan.answer1.isChecked = false
        }

        jobProfileBinding.workExperience.answer1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                jobProfileBinding.workExperience.answer2.isChecked = false
        }

        jobProfileBinding.workExperience.answer2.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked)
                jobProfileBinding.workExperience.answer1.isChecked = false
        }
    }
}