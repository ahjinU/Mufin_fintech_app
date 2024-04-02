'use client';

import { FlexBox, TinyButton } from '@/components';
import { useState, useEffect } from 'react';
import { SavingsListType } from '../../_types';
import SavingsApis from '../../_apis';
import { useRouter } from 'next/navigation';

function Title({ name }: { name: string }) {
  return (
    <section className="flex items-center gap-[1rem]">
      <h2 className="custom-semibold-text text-custom-purple">{name}</h2>
    </section>
  );
}

function ContentRow({ keyName, value }: { keyName: string; value: string }) {
  return (
    <section className="flex justify-between custom-medium-text">
      <span>{keyName}</span>
      <span>{value}</span>
    </section>
  );
}

export default function ApplySavings() {
  const [savingsList, setSavingsList] = useState<SavingsListType[]>([]);
  const { getSavingsProductList } = SavingsApis();
  const router = useRouter();

  useEffect(() => {
    const fetchData = async () => {
      const savingsListData = await getSavingsProductList();
      setSavingsList(savingsListData?.data?.savingsList);
    };

    fetchData();
  }, []);

  return (
    <>
      <section className="w-full p-[1.2rem] flex flex-col gap-[1rem] relative min-h-screen">
        {savingsList?.map((savings, index) => {
          return (
            <FlexBox
              key={`savings-${index}`}
              isDivided={true}
              topChildren={
                <div className="flex flex-row justify-between items-center mt-[-0.5rem]">
                  <Title
                    name={
                      savings.name.length > 20
                        ? savings.name.slice(0, 20) + ' ...'
                        : savings.name
                    }
                  />
                  <TinyButton
                    label="신청"
                    handleClick={() =>
                      router.push(`/savings/apply/${savings.savingsUuid}/step1`)
                    }
                  />
                </div>
              }
              bottomChildren={
                <div className="flex flex-col gap-[0.5rem] my-[-0.7rem]">
                  <ContentRow keyName="이자율" value={`${savings.interest}%`} />
                  <ContentRow
                    keyName="적금 기간"
                    value={`${savings.period}개월`}
                  />
                </div>
              }
            />
          );
        })}
      </section>
    </>
  );
}
