package com.example.nobelprizesearch.model.api_response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class LaureateApiResponse {
    @SerializedName("id")
    private final String id;

    @SerializedName("fullName")
    private final LaureateNameApiResponse nameOfIndividual;

    @SerializedName("orgName")
    private final LaureateNameApiResponse nameOfOrganization;

    private final MotivationApiResponse motivation;

    public LaureateApiResponse(String id, LaureateNameApiResponse fullName, LaureateNameApiResponse orgName, MotivationApiResponse motivation) {
        this.id = id;
        this.nameOfIndividual = fullName;
        this.nameOfOrganization = orgName;
        this.motivation = motivation;
    }

    public String getId() {
        return id;
    }

    public LaureateNameApiResponse getNameOfIndividual() {
        return nameOfIndividual;
    }

    public MotivationApiResponse getMotivation() {
        return motivation;
    }

    public LaureateNameApiResponse getNameOfOrganization() {
        return nameOfOrganization;
    }

    /** Returns nameOfIndividual, orgName or combination of both*/
    public LaureateNameApiResponse getProperName() throws IllegalStateException {
        boolean isPersonsNameValid = LaureateNameApiResponse.isValid(nameOfIndividual);
        boolean isOrganisationNameValid = LaureateNameApiResponse.isValid(nameOfOrganization);

        if (isPersonsNameValid && isOrganisationNameValid) {
            return new LaureateNameApiResponse(nameOfIndividual.getName() + " : " + nameOfOrganization.getName());
        }

        if (isPersonsNameValid) {
            return nameOfIndividual;
        }

        if (isOrganisationNameValid) {
            return nameOfOrganization;
        }

        throw new IllegalStateException("None of the names are valid");
    }

    @NonNull
    @Override
    public String toString() {
        return "Name = " + nameOfIndividual + " id = " + id + ", motivation = ";
    }
}


