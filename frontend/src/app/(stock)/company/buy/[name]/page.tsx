'use client';

import { useState, useEffect } from 'react';
import { toCompanyKoreanName, toCompanyEnglishName } from '../../_utils';
import {
  GuideText,
  FlexBox,
  MoneyInfoElement,
  OtherInfoElement,
  Button,
  Header,
  BackButton,
  MoneyShow,
  AlertConfirmModal,
} from '@/components';
import { StockBuySell } from '../../_components/StockBuySell';
import StockChartApis from '../../_apis';
import { commaNum } from '@/utils/commaNum';
import { useRouter } from 'next/navigation';

export default function Buy({ params }: { params: { name: string } }) {
  const companyName = toCompanyKoreanName(params.name);
  const [price, setPrice] = useState<number>();
  const [quantity, setQuantity] = useState<number>();
  const totalPrice = price && quantity ? price * quantity : 0;
  const [chocoChipPocket, setChocoChipPocket] = useState<number>(0);
  const [companyData, setCompanyData] = useState({
    price: 0,
    incomeRatio: 0,
    transCnt: 0,
    imageUrl: undefined,
    upperLimitPrice: 0,
    lowerLimitPrice: 0,
  });
  const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

  const { getChocoChipPocketData, buyStock, getStockDetail } = StockChartApis();
  const router = useRouter();

  useEffect(() => {
    (async function () {
      const data = await getChocoChipPocketData();
      setChocoChipPocket(data.data.balanceToday);
      console.log(data.data);
    })();
  }, []);

  useEffect(() => {
    companyName &&
      (async function () {
        const data = await getStockDetail(companyName);
        setCompanyData(data.data);
      })();
  }, []);

  return (
    <>
      <Header>
        <BackButton label={`${companyName} 회사`} />
      </Header>
      <main className="p-[1.2rem] flex flex-col gap-[1rem]">
        <GuideText text="매수는 주식을 사는 것을 의미해요!" />

        <FlexBox
          isDivided={false}
          topChildren={
            <MoneyInfoElement
              imageSrc="/images/icon-dollar.png"
              leftHighlightText={`${commaNum(chocoChipPocket)} 초코칩`}
              leftExplainText="내 초코칩 보관함"
              buttonOption="RIGHT_ARROW"
            ></MoneyInfoElement>
          }
        />

        <FlexBox
          isDivided={false}
          topChildren={
            <OtherInfoElement
              imageSrc={companyData.imageUrl}
              leftHighlightText={`${companyName} 회사`}
              leftExplainText={`오늘 거래량 ${companyData.transCnt}주`}
              rightHighlightText={`${commaNum(companyData.price)} 초코칩`}
              rightExplainText={`오전 9시보다 ${companyData.incomeRatio}%`}
              state={companyData.incomeRatio >= 0 ? 'UP' : 'DOWN'}
            ></OtherInfoElement>
          }
        />

        <MoneyShow
          mode="DIVIDED"
          text={['구매 하한가', '구매 상한가']}
          money={[
            commaNum(companyData.lowerLimitPrice).toString(),
            commaNum(companyData.upperLimitPrice).toString(),
          ]}
          unit=""
        ></MoneyShow>

        <StockBuySell
          mode="BUY"
          price={price}
          quantity={quantity}
          handlePrice={setPrice}
          handleQuantity={setQuantity}
          totalPrice={totalPrice}
          upperPrice={companyData.upperLimitPrice}
          lowerPrice={companyData.lowerLimitPrice}
        />

        <Button
          mode={price && quantity ? 'ACTIVE' : 'NON_ACTIVE'}
          label="확인"
          onClick={
            companyName && price && quantity
              ? async () => {
                  const result = await buyStock(companyName, price, quantity);
                  if (!result.message.includes('성공')) setIsModalOpen(true);
                  else
                    router.replace(
                      `/company/${toCompanyEnglishName(companyName)}`,
                    );
                }
              : undefined
          }
        />
      </main>

      {isModalOpen && (
        <div className="absolute top-0 left-0 size-full bg-custom-black-with-opacity flex justify-center">
          <AlertConfirmModal
            text="초코칩 보관함에 초코칩이 부족해요! 구매 가격과 수량을 조정해주세요."
            isOpen={isModalOpen}
            mode="ONLYCLOSE"
            handleClickClose={() => setIsModalOpen(false)}
            handleClickOkay={() => {}}
            handleClickNo={() => {}}
          />
        </div>
      )}
    </>
  );
}
