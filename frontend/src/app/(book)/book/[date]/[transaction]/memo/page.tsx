'use client';

import {
  AlertConfirmModal,
  Button,
  ComplexInput,
  FlexBox,
  OtherInfoElement,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import { useEffect, useState } from 'react';
import useBookStore from '../../../_store';
import BookApis from '../../../_apis';
import { useRouter } from 'next/navigation';

export default function BookListPost() {
  const router = useRouter();
  const [content, setContent] = useState<string>();
  const [data, setData] = useState<TransactionType>();
  const [isOpen, setIsOpen] = useState<boolean>();
  const { selectedTransaction } = useBookStore();
  const { getOneTransaction, postMemo } = BookApis();
  useEffect(() => {
    (async function () {
      const res = await getOneTransaction({
        transactionUUID: selectedTransaction.transactionUuid,
        type: selectedTransaction.code,
      });
      console.log(res);
      setContent(res?.data?.memo);
      setData(res?.data);
    })();
  }, [selectedTransaction]);

  const handleClickOkay = async () => {
    const res =
      data?.code &&
      content &&
      data?.transactionUuid &&
      (await postMemo({
        type: data?.code,
        transactionUuid: data?.transactionUuid,
        memo: content,
      }));
    res?.message === '메모 작성에 성공하였습니다.' && router.back();
    console.log(res);
  };

  return (
    <div className="p-[1.2rem] w-full flex flex-col gap-[26rem]">
      <div className="w-full flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          mode="LIST"
          topChildren={
            <OtherInfoElement
              leftExplainText={`${data?.code}`}
              leftHighlightText={`${data?.counterpartyName}`}
              money={`${commaNum(data?.amount)}원`}
              state={`${data && data?.amount > 0 ? 'UP' : 'DOWN'}`}
            />
          }
        />
        <ComplexInput label={'메모를 남길 수 있어요!'} mode={'NONE'}>
          <textarea
            className="block w-full p-4 border rounded-[0.8rem] shadow-sm 
          focus:outline-none focus:ring-2 focus:custom-purple focus:border-transparent 
          resize-none text-customb-black custom-medium-text"
            rows={4}
            placeholder="내용을 입력해주세요..."
            value={content}
            onChange={(e) => {
              setContent(e.target.value);
            }}
          />
        </ComplexInput>
      </div>
      <div>
        <Button
          mode={'ACTIVE'}
          label={'저장하기'}
          onClick={() => setIsOpen(true)}
        />
      </div>
      {isOpen && (
        <div className="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 z-50 flex items-center justify-center">
          <AlertConfirmModal
            isOpen={isOpen}
            handleClickOkay={handleClickOkay}
            text={'메모를 저장할까요?'}
            handleClickNo={() => setIsOpen(false)}
          />
        </div>
      )}
    </div>
  );
}
