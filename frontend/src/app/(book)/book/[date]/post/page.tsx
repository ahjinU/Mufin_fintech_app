'use client';

import {
  AlertConfirmModal,
  Button,
  ComplexInput,
  Input,
  MoneyShow,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import { usePathname, useRouter } from 'next/navigation';
import { useEffect, useState } from 'react';
import useBookStore from '../../_store';
import BookApis from '../../_apis';
import { format } from 'date-fns';

export default function BookListPost() {
  const router = useRouter();
  const [title, setTitle] = useState();
  const [amount, setAmount] = useState();
  const [isOpen, setIsOpen] = useState<boolean>(false);
  const [isActive, setIsActive] = useState<boolean>(false);

  const { selectedDate } = useBookStore();
  const { postDayCash } = BookApis();

  useEffect(() => {
    amount && title ? setIsActive(true) : setIsActive(false);
  }, [amount, title]);

  const savehandler = async () => {
    setIsOpen(true);
  };

  const handleClickOkay = async () => {
    const data = {
      date: selectedDate && format(selectedDate, 'yyyy-MM-dd'),
      usage: title,
      amount: amount && amount * -1,
    };

    const res = await postDayCash(data);

    res?.message === '현금 거래내역 추가에 성공하였습니다.' && router.back();
  };

  return (
    <div className="p-[1.2rem] w-full flex-col ">
      <div className="w-full flex flex-col gap-[1rem]">
        <MoneyShow
          mode={'DIVIDED'}
          text={['지출', '수입']}
          money={[commaNum(-600), commaNum(5000)]}
          unit={'원'}
        />
        <ComplexInput label={'어디에서 또는 무엇에 사용했나요?'} mode={'ERROR'}>
          <Input value={title} setValue={setTitle} />
        </ComplexInput>
        <ComplexInput label={'얼마를 사용했나요?'} mode={'ERROR'}>
          <div className="flex flex-row items-end gap-[0.5rem]">
            <Input value={amount} setValue={setAmount} width="5rem" />
            <p className="custom-semibold-text text-custom-black">원</p>
          </div>
        </ComplexInput>
      </div>
      <div className="fixed bottom-[6rem] left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Button
          mode={isActive ? 'ACTIVE' : 'NON_ACTIVE'}
          label={'추가하기'}
          onClick={savehandler}
        />
      </div>
      {isOpen && (
        <div className="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 z-50 flex items-center justify-center">
          <AlertConfirmModal
            isOpen={isOpen}
            handleClickOkay={handleClickOkay}
            text={'지출 내역을 추가하시겠어요?'}
            handleClickNo={() => setIsOpen(false)}
          />
        </div>
      )}
    </div>
  );
}
