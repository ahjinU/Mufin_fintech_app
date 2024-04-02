package com.a502.backend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    API_ERROR_INTERNAL_SERVER(500, "G001", "서버 오류"),
    API_ERROR_INPUT_INVALID_VALUE(409, "G002", "잘못된 입력"),
    API_ERROR_NO_AUTHORIZATION(403, "G002", "권한 없음"),

    // User
    API_ERROR_USER_NOT_EXIST(400, "U001", "존재하지 않는 회원입니다."),
    API_ERROR_USER_INCORRECT_PASSWORD(401, "U002", "비밀번호가 잘못되었습니다. 다시 시도해주세요."),
    API_ERROR_TELEPHONE_DUPLICATION_EXIST(409, "U003", "이미 해당 번호를 사용 중 입니다."),
    API_ERROR_EMAIL_DUPLICATION_EXIST(409, "U004", "이미 해당 이메일을 사용 중 입니다."),
    API_ERROR_SESSION_EXPIRED_OR_NOT_FOUND(401, "U005", "토큰이 존재하지 않습니다."),
    API_ERROR_TEMPORARY_UUID_NOT_EXIST(410, "U006", "존재하지 않는 UUID입니다."),
    API_ERROR_USER_ACCESSTOKEN_EXPIRED(401, "U007", "Access Token이 만료되었습니다."),
    API_ERROR_USER_DELETE(410, "U008",  "탈퇴한 회원입니다."),
    API_ERROR_USER_NOT_EXIST_MY_KIDS(400, "U009", "등록된 자녀가 없습니다."),
    API_ERROR_USER_NOT_PARENT(400, "U010", "부모 회원만 요청할 수 있는 기능입니다."),
    API_ERROR_USER_NOT_COMPLETE_TELEPHONE_CHECK(401, "U010", "핸드폰 중복 검사를 먼저 해 주세요."),
    API_ERROR_USER_NOT_COMPLETE_EMAIL_CHECK(401, "U011", "이메일 중복 검사 및 핸드폰 중복 검사를 확인해 주세요."),


    //Allowance
    API_ERROR_NOT_TIME_FORMAT(400, "AL001", "시간 포맷이 아닙니다."),

    // Account
    API_ERROR_ACCOUNT_NOT_EXIST(400, "A001", "존재하지 않는 계좌 입니다."),
    API_ERROR_ACCOUNT_IS_STOPPED(422, "A002","거래가 정지된 계좌입니다."),
    API_ERROR_ACCOUNT_INSUFFICIENT_BALANCE(422, "A003", "계좌에 잔액이 부족합니다."),
    API_ERROR_ACCOUNT_IS_NOT_YOURS(400, "A004", "접근권한이 없는 계좌입니다."),
    API_ERROR_CODE_NOT_EXIST(400, "A005", "존재하지 않는 코드 입니다."),
    API_ERROR_NOT_CREATED(500, "A006", "계좌 생성에 실패하였습니다."),
    API_ERROR_ACCOUNT_IS_ALREADY_EXIST(400, "A007", "이미 입출금 계좌가 존재합니다."),
    API_ERROR_DEFAULT_ACCOUNT_NOT_EXIST(400, "A008", "입출금 계좌를 먼저 생성해주세요"),

    // AccountDetil
    API_ERROR_ACCOUNT_DETAIL_NOT_EXIST(400, "AD01", "존재하지 않는 계좌 거래 내역 입니다."),

    // Keypad
    API_ERROR_KEYPAD_TIMEOUT(400, "K001", "키패드 요청 시간이 만료되었습니다."),

    // Alert
    API_ERROR_ALERT_NOT_EXIST(400, "AL01", "존재하지 않는 알림 입니다."),

    // CashDetail
    API_ERROR_CASHDETAIL_NOT_EXIST(400, "CD01", "존재하지 않는 현금 거래내역 입니다."),

    // Memo
    API_ERROR_MEMO_NOT_EXIST(400, "M001", "존재하지 않는 메모입니다."),
    API_ERROR_MEMO_NOT_EXIST_CODE(400, "M002", "존재하지 않는 코드 입니다."),

    // Receipt
    API_ERROR_NOT_RECEIPT(404, "R001", "영수증 형식과 다릅니다. 사진을 다시 찍어주세요."),
    API_ERROR_RECEIPT_NOT_COINCIDE_PRICE(400,"R002","해당 거래내역과 금액이 일치 하지 않습니다."),
    API_ERROR_RECEIPT_NOT_COINCIDE_STORE(400,"R003","상호명이 일치하지 않습니다."),
    API_ERROR_RECEIPT_FAIL_CONVERT_TO_TEXT(500,"R004","서버상의 이유로 텍스트 변환에 실패했습니다. 잠시 후 다시 요청해 주세요."),
    API_ERROR_RECEIPT_NOT_KNOWN_EXTENSION(400, "R005", "확장자를 알 수 없습니다."),
    API_ERROR_UNSUPPORTED_FILE_EXTENSION(400,"R006","지원하지 않는 확장자 입니다. jpg 혹은 png 파일을 사용하세요."),
    API_ERROR_OCR_CONNECTION(500,"R007","OCR 변환 서버의 에러가 발생했습니다. 잠시 후 다시 요청해 주세요."),


    // ReceiptDetail
    API_ERROR_RECEIPT_DETAIL_NOT_EXIST(400, "RD01", "존재하지 않는 영수증 내역입니다."),

    // Stock
    API_ERROR_STOCK_NOT_EXIST(400, "S001", "존재하지 않는 주식입니다."),

    // StockDetail
    API_ERROR_STOCK_DETAIL_NOT_EXIST(400, "SD01", "존재하지 않는 주식정보입니다."),
    API_ERROR_STOCK_PRICE_OUT_OF_RANGE(400, "SD02", "상한가와 하한가에 맞는 주식 액수를 입력해주세요."),

    // StockHolding
    API_ERROR_STOCK_HOLDING_NOT_EXIST(400, "SH01", "충분한 주식을 보유하고 있지 않습니다."),
    API_ERROR_STOCK_HOIDING_NOT_ENOUGH(400, "SH02", "보유 주식이 충분하지 않습니다."),

    // StockSell
    API_ERROR_STOCKSELL_NOT_EXIST(400, "SS01", "존재하지 않는 주식매도 요청입니다."),
    API_ERROR_STOCKSELL_STOCK_IS_NOT_ENOUGH(400, "SS02", "거래량이 충분하지 않습니다."),

    // StockBuy
    API_ERROR_STOCKBUY_NOT_EXIST(400, "SB01", "존재하지 않는 주식매수 요청입니다."),
    API_ERROR_STOCKBUY_STOCK_IS_NOT_ENOUGH(400, "SB02", "거래량이 충분하지 않습니다."),

    // Parking
    API_ERROR_PARKING_NOT_EXIST(400, "P001", "존재하지 않는 파킹통장 입니다."),
    API_ERROR_PARKING_NOT_ENOUGH_BALANCE(400, "P002", "파킹통장에 잔액이 부족하여 주문할 수 없습니다."),

    // ParkingDetail
    API_ERROR_PARKING_DETAIL_NOT_EXIST(400, "PD01", "존재하지 않는 파킹통장 거래내역 입니다."),

    // Loan
    API_ERROR_LOAN_NOT_EXIST(400, "L001", "존재하지 않는 대출 상품입니다."),
    API_ERROR_LOAN_NOT_EXIST_FOR_PARENTS(400, "L002","대출 받은 내역이 없습니다."),
    API_ERROR_LOAN_REQUESTED_NOT_EXIST_FOR_PARENTS(400,"L003","대출 요청 내역이 없습니다."),

    // LoanDetail
    API_ERROR_LOAN_DETAIL_NOT_EXIST(400, "LD01", "존재하지 않는 대출 납부 내역 입니다."),

    // LoanConversation
    API_ERROR_LOAN_CONVERSATION_NOT_EXIST(400, "LC01", "존재하지 않는 대출 대화입니다."),

    // LoanRefusal
    API_ERROR_LOAN_REFUSAL_NOT_EXIST(400, "LR01", "존재하지 않는 대출 거절입니다."),

    // Saving
    API_ERROR_SAVINGS_NOT_EXIST(400, "SV01", "존재하지 않는 적금 상품입니다."),
    API_ERROR_SAVINGS_DELETE(400, "SV02", "해당 적금 상품에 가입되어 있는 계좌가 존재합니다."),
    API_ERROR_SAVINGS_NOT_EXIST_MY_CHILD(400,"SV03", "적금에 가입한 자녀가 없습니다."),

    // Ranking
    API_ERROR_RANKING_LIST_FAIL(400, "R001", "랭킹 정보를 불러오는데 실패했습니다."),
    API_ERROR_RANKING_CHILD_NOT_EXIST(400, "R002", "랭킹 정보에 회원 정보와 일치하는 사람이 없습니다.")
    ;
    private final int status;
    private final String code;
    private final String message;
}
