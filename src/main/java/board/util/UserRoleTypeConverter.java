package board.util;

import board.entity.constant.UserRoleType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserRoleTypeConverter implements AttributeConverter<UserRoleType, String> {

    //java에서 DB에 저장될 떄 어떻게 바꿔줄 것인가
    @Override
    public String convertToDatabaseColumn(UserRoleType userRoleType) {
        return userRoleType.getRoleType();
    }


    // DB에서 꺼낼 떄 db string -> java enum
    @Override
    public UserRoleType convertToEntityAttribute(String string) {
//        return UserRoleType.valueOf(string); //string = null일떄 등 에러 발생 가능성
        return UserRoleType.getInstance(string);
    }
}
