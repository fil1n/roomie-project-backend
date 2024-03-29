package com.github.fil1n.jacksonClasses;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.fil1n.models.Group;
import com.github.fil1n.models.User;

import java.io.IOException;
import java.util.Base64;

public class UserSerializerForOwner extends StdSerializer<User> {

    protected UserSerializerForOwner(Class<User> t) {
        super(t);
    }

    protected  UserSerializerForOwner() {
        this(null);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("name", user.getName());
        jsonGenerator.writeNumberField("age", user.getAge());
        jsonGenerator.writeStringField("sex", user.getSex().toString());
        jsonGenerator.writeNumberField("rentalPeriod", user.getRentalPeriod());
        jsonGenerator.writeNumberField("maxRoommatesNumber", user.getMaxRoommatesNumber());


        if (user.getUserInfo() != null) {
            jsonGenerator.writeStringField("userInfo", user.getUserInfo());
        }

        if (user.getBirthCountry() != null) {
            jsonGenerator.writeStringField("birthCountry", user.getBirthCountry().getName());
        }

        if (user.getNativeCity() != null) {
            jsonGenerator.writeStringField("birthCity", user.getNativeCity().getName());
        }

        if (user.getCurrentCity() != null) {
            jsonGenerator.writeStringField("currentCity", user.getCurrentCity().getName());
        }

        if (user.getUserInfo() != null) {
            jsonGenerator.writeStringField("userInfo", user.getUserInfo());
        }

        if (user.getPhoto() != null) {
            jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(user.getPhoto()));
        }

        jsonGenerator.writeStringField("birthDate", user.getBirthDate());

        if (user.getUniversity() != null) {
            jsonGenerator.writeNumberField("universityId", user.getUniversity().getId());
            jsonGenerator.writeStringField("universityName", user.getUniversity().getName());
        }

        if (user.getFaculty() != null) {
            jsonGenerator.writeStringField("speciality", user.getFaculty().getName());
        }

        jsonGenerator.writeArrayFieldStart("languages");
        for (int i = 0; i < user.getUserLanguageList().size(); ++i) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("languageId", user.getUserLanguageList().get(i).getId());
            jsonGenerator.writeStringField("languageName", user.getUserLanguageList().get(i).getName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

        if (user.getHabbitList() != null) {
            jsonGenerator.writeArrayFieldStart("badHabits");
            for (int i = 0; i < user.getHabbitList().size(); ++i) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("habitName", user.getHabbitList().get(i).getName());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
        }

        jsonGenerator.writeStringField("phoneNumber", user.getPhone());

        jsonGenerator.writeArrayFieldStart("groups");

        if (user.getOwnedGroup() != null) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", user.getOwnedGroup().getId());
            jsonGenerator.writeStringField("name", user.getOwnedGroup().getName());
            jsonGenerator.writeStringField("city", user.getOwnedGroup().getCity().getName());
            jsonGenerator.writeStringField("groupInfo", user.getOwnedGroup().getGroupInfo());
            jsonGenerator.writeNumberField("memberNumber", user.getOwnedGroup().getTrustedUsers().size() + 1);
            jsonGenerator.writeNumberField("peopleNumber", user.getOwnedGroup().getTrustedUsers().size() + user.getOwnedGroup().getUntrustedFollowers().size());
            jsonGenerator.writeNumberField("rentalPeriod", user.getOwnedGroup().getRentalPeriod());
            jsonGenerator.writeNumberField("free", user.getOwnedGroup().getMAX_NUM_OF_USERS() - user.getOwnedGroup().getTrustedUsers().size() - 1);

            jsonGenerator.writeArrayFieldStart("members");

            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", user.getId());
                jsonGenerator.writeStringField("name", user.getName());
                jsonGenerator.writeNumberField("age", user.getAge());

                if (user.getUserInfo() != null) {
                    jsonGenerator.writeStringField("userInfo", user.getUserInfo());
                }

                if (user.getPhoto() != null) {
                    jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(user.getPhoto()));
                }

                jsonGenerator.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }

            user.getOwnedGroup().getTrustedUsers().forEach(
                    member -> {
                        try {
                            jsonGenerator.writeStartObject();
                            jsonGenerator.writeNumberField("id", member.getId());
                            jsonGenerator.writeStringField("name", member.getName());
                            jsonGenerator.writeNumberField("age", member.getAge());

                            if (user.getUserInfo() != null) {
                                jsonGenerator.writeStringField("userInfo", member.getUserInfo());
                            }

                            if (member.getPhoto() != null) {
                                jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(member.getPhoto()));
                            }

                            jsonGenerator.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );

            jsonGenerator.writeEndArray();

            jsonGenerator.writeArrayFieldStart("applications");
            user.getOwnedGroup().getUntrustedFollowers().forEach(
                    member -> {
                        try {
                            jsonGenerator.writeStartObject();
                            jsonGenerator.writeNumberField("id", member.getId());
                            jsonGenerator.writeStringField("name", member.getName());
                            jsonGenerator.writeNumberField("age", member.getAge());

                            if (user.getUserInfo() != null) {
                                jsonGenerator.writeStringField("userInfo", member.getUserInfo());
                            }

                            if (member.getPhoto() != null) {
                                jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(member.getPhoto()));
                            }

                            jsonGenerator.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );

            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
        //

        user.getWhereIsTrusted().forEach(
            group -> {
                try {
                    jsonGenerator.writeNumberField("id", group.getId());
                    jsonGenerator.writeStringField("name", group.getName());
                    jsonGenerator.writeStringField("city", group.getCity().getName());
                    jsonGenerator.writeStringField("groupInfo", group.getGroupInfo());
                    jsonGenerator.writeNumberField("memberNumber", group.getTrustedUsers().size());
                    jsonGenerator.writeNumberField("peopleNumber", group.getTrustedUsers().size() + group.getUntrustedFollowers().size());
                    jsonGenerator.writeNumberField("rentalPeriod", group.getRentalPeriod());
                    jsonGenerator.writeNumberField("free", group.getMAX_NUM_OF_USERS() - group.getTrustedUsers().size());

                    jsonGenerator.writeArrayFieldStart("members");
                    group.getTrustedUsers().forEach(
                            member -> {
                                try {
                                    jsonGenerator.writeStartObject();
                                    jsonGenerator.writeNumberField("id", member.getId());
                                    jsonGenerator.writeStringField("name", member.getName());
                                    jsonGenerator.writeNumberField("age", member.getAge());

                                    if (user.getUserInfo() != null) {
                                        jsonGenerator.writeStringField("userInfo", member.getUserInfo());
                                    }

                                    if (member.getPhoto() != null) {
                                        jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(member.getPhoto()));
                                    }

                                    jsonGenerator.writeEndObject();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );

                    jsonGenerator.writeEndArray();

                    jsonGenerator.writeArrayFieldStart("applications");

                    group.getUntrustedFollowers().forEach(
                            follower -> {
                                try {
                                    jsonGenerator.writeStartObject();
                                    jsonGenerator.writeNumberField("id", follower.getId());
                                    jsonGenerator.writeStringField("name", follower.getName());
                                    jsonGenerator.writeNumberField("age", follower.getAge());

                                    if (user.getUserInfo() != null) {
                                        jsonGenerator.writeStringField("userInfo", follower.getUserInfo());
                                    }

                                    if (follower.getPhoto() != null) {
                                        jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(follower.getPhoto()));
                                    }

                                    jsonGenerator.writeEndObject();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                    );

                    jsonGenerator.writeEndArray();
                    jsonGenerator.writeEndObject();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        );


        // other groups

        for (int i = 0; i < user.getWhereIsUntrusted().size(); ++i) {
            Group group = user.getWhereIsUntrusted().get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", group.getId());
            jsonGenerator.writeStringField("name", group.getName());
            jsonGenerator.writeStringField("city", group.getCity().getName());
            jsonGenerator.writeStringField("groupInfo", group.getGroupInfo());
            jsonGenerator.writeNumberField("memberNumber", group.getTrustedUsers().size());
            jsonGenerator.writeNumberField("peopleNumber", group.getTrustedUsers().size() + group.getUntrustedFollowers().size());
            jsonGenerator.writeNumberField("rentalPeriod", group.getRentalPeriod());
            jsonGenerator.writeNumberField("free", group.getMAX_NUM_OF_USERS() - group.getTrustedUsers().size());

            jsonGenerator.writeArrayFieldStart("members");
            group.getTrustedUsers().forEach(
                    member -> {
                        try {
                            jsonGenerator.writeStartObject();
                            jsonGenerator.writeNumberField("id", member.getId());
                            jsonGenerator.writeStringField("name", member.getName());
                            jsonGenerator.writeNumberField("age", member.getAge());

                            if (user.getUserInfo() != null) {
                                jsonGenerator.writeStringField("userInfo", member.getUserInfo());
                            }

                            if (member.getPhoto() != null) {
                                jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(member.getPhoto()));
                            }

                            jsonGenerator.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );

            jsonGenerator.writeEndArray();

            jsonGenerator.writeArrayFieldStart("applications");

            group.getUntrustedFollowers().forEach(
                    follower -> {
                        try {
                                jsonGenerator.writeStartObject();
                                jsonGenerator.writeNumberField("id", follower.getId());
                                jsonGenerator.writeStringField("name", follower.getName());
                                jsonGenerator.writeNumberField("age", follower.getAge());

                                if (user.getUserInfo() != null) {
                                    jsonGenerator.writeStringField("userInfo", follower.getUserInfo());
                                }

                                if (follower.getPhoto() != null) {
                                    jsonGenerator.writeStringField("photo", Base64.getEncoder().encodeToString(follower.getPhoto()));
                                }

                                jsonGenerator.writeEndObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
            );

            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }


        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
