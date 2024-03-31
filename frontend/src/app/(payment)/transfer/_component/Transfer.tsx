import Link from 'next/link';
import useUserStore from '../../../_store/store';
import { useState } from 'react';
import { AccountBox, Button, Input } from '@/components';

export default function Transfer() {
  const { userData } = useUserStore();

  const [state, setState] = useState<
    'INPUT_ACCOUNT' | 'INPUT_MONEY' | 'NO_ACCOUNT' | 'NO_MONEY' |'PASSWORD'
  >('INPUT_ACCOUNT');
  const [transferData, setTransferData] = useState({ account: '', money: '' });
  const [message, setMessage] = useState('');

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    setTransferData({ ...transferData, [e.target.name]: e.target.value });
  };

  const handleNext = () => {
    // 계좌 확인 fetch
    setState('INPUT_MONEY');
    // if(){
    //   setState('NO_ACCOUNT'); // 없는 계좌
    // }
  };

  const handleTransfer = () => {
    // 돈, 송금 계좌 확인 fetch
    // setState('PASSWORD');
    // if(){
    //   setState('NO_MONEY') // 돈 없음
    // }
  };

  const whiteBoxClass =
    'w-full h-[12.6rem] rounded-[2rem] p-[2rem] bg-custom-white flex flex-col justify-center gap-[1rem]';

  const modalBackground =
    'absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center';

  return (
    <div className="mt-[0.4rem] flex flex-col gap-[1rem]">
      <AccountBox text={'내 잔액'} money={userData.balance}></AccountBox>
      <div className={whiteBoxClass}>
        <p className="custom-bold-text text-custom-black">
          어디로 돈을 보낼까요?
        </p>
        <Input
          placeholder="계좌 번호 입력"
          name="account"
          onChange={onChangeInput}
        />
      </div>
      {state == 'INPUT_MONEY' ? (
        <div className={whiteBoxClass}>
          <p className="custom-bold-text text-custom-black">얼마를 보낼까요?</p>
          <Input
            placeholder="보낼 금액 입력"
            name="money"
            onChange={onChangeInput}
          />
          <p className="custom-light-text text-custom-red">{message}</p>
        </div>
      ) : null}
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        {state == 'INPUT_ACCOUNT' ? (
          <Button label="다음" mode="ACTIVE" onClick={handleNext} />
        ) : (
          <Button label="돈 보내기" mode="ACTIVE" onClick={handleTransfer} />
        )}
      </div>
      {state == 'NO_ACCOUNT' ? <div className={modalBackground}></div> : null}
      {state == 'PASSWORD' ? <div className={modalBackground}></div> : null}
    </div>
  );
}
