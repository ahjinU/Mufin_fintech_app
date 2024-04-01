'use client';

import {
  AlertConfirmModal,
  BottomSheet,
  Button,
  ComplexInput,
  DealList,
  FlexBox,
  GuideText,
  ImageUploadBox,
  Input,
  MoneyShow,
  OtherInfoElement,
  TinyButton,
} from '@/components';
import { commaNum } from '@/utils/commaNum';
import Link from 'next/link';
import { useEffect, useState } from 'react';
import BookApis from '../../../_apis';
import { usePathname } from 'next/navigation';
import useBookStore from '../../../_store';
import { useRouter } from 'next/navigation';

export default function BookListPost() {
  const router = useRouter();

  var currentUrl = usePathname();
  var id = currentUrl?.split('/')[3];
  const [isOpen, setIsOpen] = useState<boolean>();
  const [image, setImage] = useState<File | null>(null);
  const { selectedTransaction } = useBookStore();
  const { postReceipt, getOneTransaction } = BookApis();
  const [data, setData] = useState<TransactionType>();
  const [isBottomSheet, setIsBottomSheet] = useState<boolean>();

  useEffect(() => {
    (async function () {
      const res = await getOneTransaction({
        transactionUUID: selectedTransaction.transactionUuid,
        type: selectedTransaction.code,
      });
      setData(res?.data);
    })();
  }, [selectedTransaction]);

  const handleClickOkay = async () => {
    if (image && id) {
      setIsOpen(false);
      setIsBottomSheet(true);

      const formData = new FormData();
      formData.append('file', image);
      formData.append('transactionUuid', id);
      formData.append('type', selectedTransaction?.code);
      const res = await postReceipt(formData);
      console.log(res);

      if (res?.message === '영수증 분석을 완료햐였습니다.') {
        setIsBottomSheet(false);
        router.back();
      }
    }
  };

  return (
    <div className="p-[1.2rem] flex flex-col gap-[1rem])">
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
        {data && data?.amount < 0 && (
          <ComplexInput label={'상세 사용 내역'} mode={'NONE'}>
            <div className="p-[0.3rem]">
              {data?.receipts ? (
                <div>
                  <DealList mode={'READONLY'} deals={data?.receipts} />
                  <div className="border-b border-gray-400 mt-[1rem]" />
                </div>
              ) : (
                <ImageUploadBox
                  text={'영수증 사진 업로드하기'}
                  image={image}
                  setImage={setImage}
                />
              )}
            </div>
          </ComplexInput>
        )}
        <ComplexInput label={'기록을 남길 수 있어요!'} mode={'NONE'}>
          <div>
            <div className="flex justify-end mt-[-2.5rem]">
              <Link href="./memo">
                <TinyButton
                  label={`${data && data?.memo ? '수정하기' : '작성하기'}`}
                />
              </Link>
            </div>
            <span className="flex mt-[-0.3rem] leading-[1.2rem] text-custom-dark-gray custom-light-text">
              간단한 메모를 남기거나 영수증이 없다면
              <br />
              내역을 직접 입력해보세요.
            </span>
          </div>
        </ComplexInput>
        <p className="text-custom-black custom-medium-text pl-[1rem]">
          {data && data?.memo}
        </p>
      </div>

      {image && (
        <div className="fixed bottom-[6rem] left-[1.2rem] right-[1.2rem] my-[1.2rem]">
          <Button
            onClick={() => {
              setIsOpen(true);
            }}
            mode={'ACTIVE'}
            label={'영수증 등록하기'}
          />
        </div>
      )}
      {isOpen && (
        <div className="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 z-50 flex items-center justify-center">
          <AlertConfirmModal
            isOpen={isOpen}
            handleClickOkay={handleClickOkay}
            text={'영수증을 저장할까요?'}
            handleClickNo={() => setIsOpen(false)}
          />
        </div>
      )}
      {isBottomSheet && (
        <div className="fixed top-0 left-0 w-full h-full bg-black bg-opacity-50 z-50 flex items-center justify-center">
          <BottomSheet
            size={'SMALL'}
            title={'영수증을 분석중입니다'}
            imageSrc={'/images/icon-search.png'}
            isXButtonVisible={false}
            isOpen={isBottomSheet}
          />
        </div>
      )}
    </div>
  );
}
