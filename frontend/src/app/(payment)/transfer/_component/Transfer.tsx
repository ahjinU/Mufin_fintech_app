import Link from 'next/link';
import { useState } from 'react';
import { AccountBox, Button, Input } from '@/components';

export default function Transfer() {
  const [transferData, setTransferData] = useState({ account: '', money: '' });
  const [isValidAccount, setIsValidAccount] = useState(false);
  const [isValidMoney, setIsValidMoney] = useState(false);

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    setTransferData({ ...transferData, [e.target.name]: e.target.value });
  };

  const handleNext = () => {
    // 계좌 확인 fetch
    setIsValidAccount(true);
  };

  const whiteBoxClass =
    'w-full h-[12.6rem] rounded-[2rem] p-[2rem] bg-custom-white flex flex-col justify-center gap-[1rem]';

  return (
    <div className="mt-[0.4rem] flex flex-col gap-[1rem]">
      <AccountBox />
      <div className={whiteBoxClass}>
        <p className="custom-bold-text text-custom-black">
          어디로 돈을 보낼까요?
        </p>
        <Input placeholder="계좌 번호 입력" />
      </div>
      {isValidAccount ? (
        <div className={whiteBoxClass}>
          <p className="custom-bold-text text-custom-black">얼마를 보낼까요?</p>
          <Input placeholder="보낼 금액 입력" />
        </div>
      ) : null}
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        {isValidAccount ? (
          <Button label="다음" mode="ACTIVE" onClick={handleNext} />
        ) : (
          <Button label="돈 보내기" mode="ACTIVE" onClick={handleNext} />
        )}
      </div>
    </div>
  );
}
