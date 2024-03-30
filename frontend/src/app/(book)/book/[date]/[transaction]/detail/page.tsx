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
import { useState } from 'react';
import BookApis from '../../../_apis';
import { usePathname } from 'next/navigation';

export default function BookListPost() {
  var currentUrl = usePathname();
  var id = currentUrl?.split('/')[3];
  const [title, setTitle] = useState();
  const [amount, setAmount] = useState();
  const [image, setImage] = useState<File | null>(null);

  const { postReceipt } = BookApis();
  const data = {
    storeName: '상점 A',
    transactionUUID: 'uuid1',
    type: '현금',
    amount: 50000,
    receptDetails: [
      {
        item: '상품 A',
        cnt: 2,
        price: 10000,
        total: 20000,
      },
      {
        item: '상품 B',
        cnt: 1,
        price: 20000,
        total: 20000,
      },
    ],
    content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.',
    category: '식료품',
  };

  const analysis = async () => {
    console.log(image);
    const res =
      image &&
      id &&
      (await postReceipt({
        file: image,
        transactionUuid: id,
        type: '현금',
      }));
    console.log(res);
  };

  return (
    <div className="p-[1.2rem] w-full flex flex-col gap-[10rem]">
      <div className="w-full flex flex-col gap-[1rem]">
        <FlexBox
          isDivided={false}
          mode="LIST"
          topChildren={
            <OtherInfoElement
              leftExplainText={`${data.category}`}
              leftHighlightText={`${data.storeName}`}
              money={`${commaNum(data.amount)}원`}
              state={`${data.amount > 0 ? 'UP' : 'DOWN'}`}
            />
          }
        />
        {data.amount > 0 && (
          <ComplexInput label={'상세 사용 내역'} mode={'NONE'}>
            <div className="p-[0.3rem]">
              {!data.receptDetails ? (
                <div>
                  <DealList mode={'READONLY'} deals={data.receptDetails} />
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
                  label={`${data.content ? '수정하기' : '작성하기'}`}
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
          {data.content}
        </p>
      </div>
      {image && (
        <Button onClick={analysis} mode={'ACTIVE'} label={'영수증 분석하기'} />
      )}
    </div>
  );
}
