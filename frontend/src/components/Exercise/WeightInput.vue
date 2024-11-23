<script setup lang="ts">
import MemberApiFacade from '@/api/apiFacade/MemberApiFacade'
import { FormField } from '../ui/form'
import FormItem from '../ui/form/FormItem.vue'
import FormLabel from '../ui/form/FormLabel.vue'
import FormControl from '../ui/form/FormControl.vue'
import Input from '../ui/input/Input.vue'
import FormMessage from '../ui/form/FormMessage.vue'
import { z } from 'zod'
import { useForm } from 'vee-validate'
import { toTypedSchema } from '@vee-validate/zod'
import Button from '../ui/button/Button.vue'

const { handleClickNext } = defineProps<{
  handleClickNext: () => void
}>()

const zodSchema = z.object({
  weight: z.coerce.number(),
})

const formSchema = toTypedSchema(zodSchema)

type FormValues = z.infer<typeof zodSchema>

const { data } = MemberApiFacade.useFetchUserInfo()

console.log('weight' + typeof data?.value.weight)

const { mutateAsync } = MemberApiFacade.useUpdateWeight()

const form = useForm<FormValues>({
  validationSchema: formSchema,
  initialValues: {
    weight: data?.value.weight,
  },
})

const onSubmit = form.handleSubmit((value) => {
  mutateAsync(value.weight).then(() => handleClickNext())
})
</script>

<template>
  <form @submit.prevent="onSubmit">
    <FormField v-slot="{ componentField }" name="weight">
      <FormItem>
        <FormLabel>몸무게를 입력해주세요</FormLabel>
        <FormControl>
          <Input type="number" v-bind="componentField" />
        </FormControl>
        <div class="min-h-5">
          <FormMessage />
        </div>
      </FormItem>
    </FormField>
    <div class="w-full flex justify-end">
      <Button>다음</Button>
    </div>
  </form>
</template>
