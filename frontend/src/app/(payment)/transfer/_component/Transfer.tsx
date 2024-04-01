import useFetch from '@/hooks/useFetch';
import useUserStore from '../../../_store/store';
import { useState } from 'react';
import {
  AccountBox,
  BottomSheet,
  Button,
  Input,
  PayPasswordBox,
} from '@/components';
import { useRouter } from 'next/navigation';

export default function Transfer() {
  const router = useRouter();
  const { userData } = useUserStore();
  const { postFetch } = useFetch();

  const [state, setState] = useState<
    | 'INPUT_ACCOUNT'
    | 'INPUT_MONEY'
    | 'NO_ACCOUNT'
    | 'PASSWORD'
    | 'SUCCESS'
    | 'FAIL'
  >('INPUT_ACCOUNT');
  const [transferData, setTransferData] = useState({ account: '', money: 0 });
  const [message, setMessage] = useState('');

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    setTransferData({ ...transferData, [e.target.name]: e.target.value });
  };

  const handleNext = async () => {
    const account = transferData.account;
    try {
      const res = await postFetch({
        api: '/pay/transfer',
        data: { accountNumberIn: account },
      });
      if (res.message == '존재하는 계좌입니다.') {
        setState('INPUT_MONEY');
      } else {
        setState('NO_ACCOUNT');
      }
    } catch (error) {
      console.error('송금 계좌 유효성 검사 에러', error);
    }
  };

  const handleTransfer = async () => {
    try {
      const res = await postFetch({
        api: '/pay/withdraw',
        data: {
          accountNumberOut: userData.accountNumber,
          amount: transferData.money,
        },
      });
      if (res.message == '정상적으로 출금이 가능합니다.') {
        setState('PASSWORD');
      } else if (res.message == '거래가 정지된 계좌입니다.') {
        setMessage('거래가 정지된 계좌입니다. 고객센터로 문의해주세요!');
      } else {
        setMessage('내 잔액보다 큰 금액은 보낼 수 없어요!');
      }
    } catch (error) {
      console.error('출금 계좌 유효성 검사 에러', error);
    }
  };

  const handleConfirm = async () => {
    try {
      const res = await postFetch({
        api: '/pay/account',
        data: {
          accountNumberIn: transferData.account,
          accountNumberOut: userData.accountNumber,
          amount: transferData.money,
          transType: 'ADT004',
        },
      });
      if (res.message == '송금에 성공하였습니다.') {
        setState('SUCCESS');
      } else {
        setState('FAIL');
      }
    } catch (error) {
      console.error('송금 계좌 유효성 검사 에러', error);
    }
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
          disabled={state != 'INPUT_ACCOUNT'}
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
      {state == 'NO_ACCOUNT' ? (
        <div className={modalBackground}>
          <BottomSheet
            size="MEDIUM"
            title="예금주를 확인할 수 없습니다."
            content="계좌번호를 확인하고 다시 입력해주세요."
            imageSrc="/images/icon-warning.png"
            isXButtonVisible={false}
            isOpen={true}
            onConfirm={() => setState('INPUT_ACCOUNT')}
          />
        </div>
      ) : null}
      {state == 'PASSWORD' ? (
        <div className={modalBackground}>
          <PayPasswordBox
            mode="CHECK"
            handleConfirmButton={handleConfirm}
            handleCloseButton={() => setState('INPUT_MONEY')}
            accountNumber={userData.accountNumber}
          />
        </div>
      ) : null}
      {state == 'SUCCESS' ? (
        <div className={modalBackground}>
          <BottomSheet
            size="SMALL"
            title="송금이 완료됐어요!"
            imageSrc="/images/icon-gold.png"
            isXButtonVisible={true}
            isOpen={true}
            onClose={() => router.replace('/')}
          />
        </div>
      ) : null}
      {state == 'FAIL' ? (
        <div className={modalBackground}>
          <BottomSheet
            size="SMALL"
            title="송금에 실패했습니다."
            imageSrc="/images/icon-warning.png"
            isXButtonVisible={true}
            isOpen={true}
            onClose={() => setState('INPUT_ACCOUNT')}
          />
        </div>
      ) : null}
    </div>
  );
}
