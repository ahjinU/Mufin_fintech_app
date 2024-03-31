'use client';

import {
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

export default function BookListPost() {
  var currentUrl = usePathname();
  var id = currentUrl?.split('/')[3];
  const [title, setTitle] = useState();
  const [amount, setAmount] = useState();
  const [image, setImage] = useState<File | null>(null);
  const { selectedTransaction } = useBookStore();
  const { postReceipt, getOneTransaction } = BookApis();
  const [data, setData] = useState<OneTransactionType>();

  useEffect(() => {
    (async function () {
      const res = await getOneTransaction({
        transactionUUID: selectedTransaction.transactionUuid,
        type: selectedTransaction.code,
      });
      console.log(data);
      // setData(res?.data)
    })();
  }, [selectedTransaction]);

  const analysis = async () => {
    if (image && id) {
      const formData = new FormData();
      formData.append('file', image);
      formData.append('transactionUuid', id);
      formData.append('type', '현금');
      try {
        const res = await postReceipt(formData);
        console.log(res);
      } catch (error) {
        console.error('Error analyzing receipt:', error);
      }
    }
  };

  return (
    <div className="p-[1.2rem] w-full flex flex-col gap-[10rem]">
      <div className="w-full flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          mode="LIST"
          topChildren={
            <OtherInfoElement
              leftExplainText={`${data?.type}`}
              leftHighlightText={`${data?.storeName}`}
              money={`${commaNum(data?.amount)}원`}
              state={`${data && data?.amount > 0 ? 'UP' : 'DOWN'}`}
            />
          }
        />
        {data && data?.amount > 0 && (
          <ComplexInput label={'상세 사용 내역'} mode={'NONE'}>
            <div className="p-[0.3rem]">
              {!data.receptDetails ? (
                <div>
                  <DealList mode={'READONLY'} deals={data?.receptDetails} />
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
        <Button onClick={analysis} mode={'ACTIVE'} label={'영수증 분석하기'} />
      )}
    </div>
  );
}
